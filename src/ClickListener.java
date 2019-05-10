import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ClickListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        switch (Button) {
            case 1:
                Button1 = "January";
                break;
            case 2:
                monthString = "February";
                break;

            System.out.println("Log bloody in");
        }

    }
}
