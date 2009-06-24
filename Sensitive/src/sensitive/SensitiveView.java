/*
 * SensitiveView.java
 */
package sensitive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

/**
 * The application's main frame.
 */
public class SensitiveView extends FrameView implements ActionListener, Runnable
{
	private static ImageIcon clic1 = new ImageIcon("/home/raphael/Documents/code/PR302/img/1clic.png");
	private static ImageIcon clic2 = new ImageIcon("/home/raphael/Documents/code/PR302/img/2clic.png");
	private static ImageIcon clic3 = new ImageIcon("/home/raphael/Documents/code/PR302/img/3clic.png");
	private static ImageIcon clic4 = new ImageIcon("/home/raphael/Documents/code/PR302/img/4clic.png");
	private static ImageIcon vert1 = new ImageIcon("/home/raphael/Documents/code/PR302/img/1vert.png");
	private static ImageIcon vert2 = new ImageIcon("/home/raphael/Documents/code/PR302/img/2vert.png");
	private static ImageIcon vert3 = new ImageIcon("/home/raphael/Documents/code/PR302/img/3vert.png");
	private static ImageIcon vert4 = new ImageIcon("/home/raphael/Documents/code/PR302/img/4vert.png");
	
	public SensitiveView(SingleFrameApplication app)
	{
		super(app);
		initComponents();
		
		jButton1.setIcon(vert1);
		jButton2.setIcon(vert2);
		jButton3.setIcon(vert3);
		jButton4.setIcon(vert4);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run()
	{		
		Capture capture = new Capture();
		ArrayList<Bouton> btns = new ArrayList<Bouton>();

		for (int k = 1; k <= 4; k++)
		{
			//System.out.println("bouton " + k);
			this.jLabel1.setText("Calibration du Bouton " + k);
			Bouton b = new Bouton(capture, jLabel2);
			btns.add(b);
			b.addListener(this);
		}
		System.out.println();
		this.jLabel1.setText("Calibration terminée");
		this.jLabel2.setText("");

		while (true)
		{
			Bouton.traiterSignal(capture.getTap(), btns);
		}
	}
		
	public void actionPerformed(ActionEvent ae)
	{
		JButton b;
		ImageIcon on;
		ImageIcon off;
		switch (ae.getID() + 1)
		{
			case 1:
				b = jButton1;
				on = clic1;
				off = vert1;
				break;
			case 2:
				b = jButton2;
				on = clic2;
				off = vert2;
				break;
			case 3:
				b = jButton3;
				on = clic3;
				off = vert3;
				break;
			case 4:
				b = jButton4;
				on = clic4;
				off = vert4;
				break;
			default:
				System.out.println("rien");
				return;
			}

		b.setIcon(on);
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException ex)
		{			
			System.out.println("Interruption de l'attente");
		}
		b.setIcon(off);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon("home/raphael/Documents/code/PR302/img/1vert.png")); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(85, 85));
        jButton1.setMinimumSize(new java.awt.Dimension(85, 85));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(160, 160));

        jButton2.setIcon(new javax.swing.ImageIcon("home/raphael/Documents/code/PR302/img/2vert.png")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(160, 160));

        jButton3.setIcon(new javax.swing.ImageIcon("home/raphael/Documents/code/PR302/img/3vert.png")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(160, 160));

        jButton4.setIcon(new javax.swing.ImageIcon("home/raphael/Documents/code/PR302/img/4vert.png")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(160, 160));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sensitive.SensitiveApp.class).getContext().getResourceMap(SensitiveView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(233, 233, 233)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
