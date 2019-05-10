import javax.swing.*;

public class GUI {

    public void startProgram(){
        JFrame Frame = new JFrame();

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setTitle("Test");
        Frame.setSize(400,400);

        JPanel panel = new JPanel();

        JLabel Label1 = new JLabel("tekst1");

        JLabel Label2 = new JLabel("tekst2");

        JLabel Label3 = new JLabel("tekst");


        panel.add(Label1);

        panel.add(Label2);

        panel.add(Label3);

        Frame.add(panel);

        Frame.setVisible(true);
    }
}
