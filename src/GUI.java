import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    public void runGui(){
        JFrame Frame = new JFrame();

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setTitle("Test");
        Frame.setSize(400,400);





        JPanel panel = new JPanel();

        panel.setBackground(Color.BLUE);

        JLabel Label1 = new JLabel("Full House!");

        JLabel Label2 = new JLabel("Klik op 'Log in' als u al heeft account heeft. \n Klik op 'Aanmelden' als u een account wilt maken. ");

        JButton Button2 = new JButton("Log in");

        JButton Button1 = new JButton("Aanmelden");

        ActionListener listener = new ClickListener();
        Button1.addActionListener(listener);

        panel.add(Label1);

        panel.add(Label2);

        panel.add(Button1);

        panel.add(Button2);

        Frame.add(panel);

        Frame.setVisible(true);
    }
}
