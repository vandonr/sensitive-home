package sensitive;

/*
 * source : http://java.sun.com/products/java-media/sound/samples/JavaSoundDemo/
 */

import java.io.*;
import javax.sound.sampled.*;

/**
 * enregistrement d'un vecteur d'entiers depuis le micro
 * 
 * @author raphael
 */
public class Capture
{    
    private static final int seuil = 400; //amplitude de declenchement du tap
    
    private TargetDataLine line;
        private AudioFormat format;
        private int bufferLengthInBytes;

    /**
     * Ce constructeur permet de spécifier 
     * les paramètres à utiliser pour la capture.
     * Par défaut, on est en 44,1 kHz, 16 bits, stereo
     * 
     * @param sampleRate 44100 - 22050 - 16000 - 11025 - 8000
     * @param sampleSizeInBits 16 - 8
     * @param channels 2 = stereo - 1 = mono
     */
    public Capture(float sampleRate, int sampleSizeInBits, int channels)
    {
        this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, sampleSizeInBits, channels, (sampleSizeInBits / 8) * channels, sampleRate, true);
                init();
    }

    /**
     * Constructeur par defaut :
     * 44,1 kHz, 16 bits, stereo
     */
    public Capture()
    {
                this(44100, 16, 2);
    }

        /**
         * initialise l'entrée ligne pour une capture 
         * à partir des paramètres donnés au constructeur.
         * 
         * appellée dans le constructeur.
         */
    private void init()
    {
                // define the required attributes for our line
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

                //make sure a compatible line is supported.
                if (!AudioSystem.isLineSupported(info))
                {
                        System.out.println("Line matching " + info + " not supported.");
                        return;
                }

                // get the target data line for capture.
                try
                {
                        line = (TargetDataLine) AudioSystem.getLine(info);
                }
                catch (LineUnavailableException ex)
                {
                        System.out.println("Unable to open the line: " + ex);
                        return;
                }
                catch (Exception ex)
                {
                        System.out.println(ex.toString());
                        return;
                }

                bufferLengthInBytes = (line.getBufferSize() / 8) * format.getFrameSize();
    }

        /**
         * écoute l'entrée ligne jusqu'à capter un signal
         * qui dépasse la valeur Capture.seuil.
         * 
         * @return un echantillon de taille 2*bufferLengthInBytes contenant le tap détecté.
         */
    public int[][] getTap()
    {
                int[][] audioData = null;
                byte[] data = new byte[bufferLengthInBytes]; //tampon de lecture

                try
                {
                        line.open(format, line.getBufferSize());
                }
                catch (LineUnavailableException ex)
                {
                        System.out.println(ex.toString());
                }

                line.start(); //silence, ça tourne

                while (line.read(data, 0, bufferLengthInBytes) != -1)
                {
                        if (audioData == null && Outils.getMax(traitementBytes(data)) >= Capture.seuil)
                        {
                                audioData = traitementBytes(data);
                        }
                        else if (audioData != null)
                        {
                                audioData = Outils.concatene(audioData, traitementBytes(data));
                                if(audioData[0].length > 20000)
                                        break;
                        }
                }
                
//              for (int i = 0; i < audioData[0].length; i++)
//                      System.out.println(audioData[0][i] + " " + audioData[1][i]);

                // we reached the end of the stream.  stop and close the line.
                line.stop();
                line.close();

                return audioData;
    }

    /**
     * Convertit le flux de bytes reçu en un tableau d'int
     * contenant les bonnes valeurs des samples
     * (si on écoute en 16 bits, il y a 2 bytes par sample
     * et il faut les concatener pour avoir la bonne valeur)
         * 
         * si il y a 2 cannaux, ils sont donnés séparemment 
         * dans ret[0][] et ret[1][]
     * 
     * /!\ l'encodage doit être PCMsigned et BigEndian
     * 
     * @param audioBytes flux reçu
     * @param sampleSize taille en bits d'un sample (8 ou 16)
     * @return valeurs des samples
     */
    private int[][] traitementBytes(byte[] audioBytes)
    {
        //on suppose qu'on est en PCMsigned, BigEndian
        int[][] intData = null;
                int chan = format.getChannels();
        int nbSamples;
                
        if (format.getSampleSizeInBits() == 16)
        {
            nbSamples = audioBytes.length / 2;
            intData = new int[chan][nbSamples/chan];
                        int MSB, LSB;
            for (int i = 0; i < nbSamples; i++)
            {
                MSB = (int) audioBytes[2 * i];
                LSB = (int) audioBytes[2 * i + 1];
                intData[i%chan][i/chan] = MSB << 8 | (255 & LSB);
            }
        }
        else if (format.getSampleSizeInBits() == 8)
        {
            nbSamples = audioBytes.length;

            //si on est en 8 bits, les bytes contiennent déjà les données
            //il suffit de tout recopier dans un tableau de int
                        //en intercalant 1/2 si on est en stereo
                        intData = new int[chan][nbSamples/chan];

                        for (int i = 0; i < nbSamples; i++)
                                intData[i%chan][i/chan] = audioBytes[i];
        }
        
        return intData;
    }
} // End class Capture
