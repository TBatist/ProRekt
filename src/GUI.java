import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {
    private Button lastClicked = new Button();
    public static JButton Button1;


    public void runGui(){
        JFrame Frame = new JFrame();

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setTitle("Test");
        Frame.setSize(600,400);

        JPanel panel = new JPanel();

        Color background = new Color(Integer.parseInt("BEE7E8", 16));
        Color background2 = new Color(Integer.parseInt("A0D2DB", 16));
        Color background3 = new Color(Integer.parseInt("7D8CC4", 16));
        Color background4 = new Color(Integer.parseInt("726DA8", 16));
        Color background5 = new Color(Integer.parseInt("594157", 16));


        panel.setBackground(background);

        JLabel Label1 = new JLabel("Full House!");

        JLabel Label2 = new JLabel("Klik op 'Log in' als u al een account heeft.   Klik op 'Aanmelden' als u een account wilt maken. ");

        JButton Button2 = new JButton("Log in");
        Button2.setBackground(background2);

        Button1 = new JButton("Aanmelden");
        Button1.setBackground(background2);

        ActionListener listener = new ClickListener();
        Button1.addActionListener(listener);

        panel.setLayout(new GridLayout(4,1));

        panel.add(Label1);

        panel.add(Label2);

        panel.add(Button1);

        panel.add(Button2);

        Frame.add(panel);


        Frame.setVisible(true);
    }

    public class ClickListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == GUI.Button1){

            }

        }
    }

}
