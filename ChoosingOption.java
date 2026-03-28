import javax.swing.JOptionPane;
public class ChoosingOption{
    public static void main(String[] args) {

        int option = JOptionPane.showConfirmDialog(
                null,
                "Do you want to change to the first class ticket?"
        );

        String result;

        if (option == JOptionPane.YES_OPTION)
            result = "Yes";
        else
            result = "Cancel";

        JOptionPane.showMessageDialog(null, "You've chosen: " + result);
    }
}