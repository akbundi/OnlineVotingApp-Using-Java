import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class OnlineVotingApp extends JFrame {
    private JPanel mainPanel;
    private ButtonGroup partyButtonGroup;
    private JButton submitButton;
    private JButton declareResultButton;
    private JTextArea resultTextArea;
    private int bjpVotes, incVotes, aapVotes, ljpVotes, shivsenaVotes;
    private Set<String> votedVoters;

    public OnlineVotingApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Online Voting App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        partyButtonGroup = new ButtonGroup();

        JRadioButton bjpRadioButton = new JRadioButton("BJP");
        JRadioButton incRadioButton = new JRadioButton("INC");
        JRadioButton aapRadioButton = new JRadioButton("AAP");
        JRadioButton ljpRadioButton = new JRadioButton("LJP");
        JRadioButton shivsenaRadioButton = new JRadioButton("ShivSena");

        partyButtonGroup.add(bjpRadioButton);
        partyButtonGroup.add(incRadioButton);
        partyButtonGroup.add(aapRadioButton);
        partyButtonGroup.add(ljpRadioButton);
        partyButtonGroup.add(shivsenaRadioButton);

        submitButton = new JButton("Submit Vote");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Voter Name:");
                
                if (votedVoters.contains(name)) {
                    JOptionPane.showMessageDialog(null, "Voter " + name + " has already voted.");
                    return;
                }
                
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Voter Age:"));

                if (age >= 18) {
                    if (bjpRadioButton.isSelected()) {
                        bjpVotes++;
                    } else if (incRadioButton.isSelected()) {
                        incVotes++;
                    } else if (aapRadioButton.isSelected()) {
                        aapVotes++;
                    } else if (ljpRadioButton.isSelected()) {
                        ljpVotes++;
                    } else if (shivsenaRadioButton.isSelected()) {
                        shivsenaVotes++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a party.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Voter must be at least 18 years old to vote.");
                }
                
                votedVoters.add(name);
                updateResultText();
            }
        });

        declareResultButton = new JButton("Declare Result");
        declareResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declareResult();
            }
        });

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);

        mainPanel.add(bjpRadioButton);
        mainPanel.add(incRadioButton);
        mainPanel.add(aapRadioButton);
        mainPanel.add(ljpRadioButton);
        mainPanel.add(shivsenaRadioButton);
        mainPanel.add(submitButton);
        mainPanel.add(declareResultButton);
        mainPanel.add(resultTextArea);

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        
        votedVoters = new HashSet<>();
    }

    private void updateResultText() {
        resultTextArea.setText("BJP Votes: " + bjpVotes +
                               "\nINC Votes: " + incVotes +
                               "\nAAP Votes: " + aapVotes +
                               "\nLJP Votes: " + ljpVotes +
                               "\nShivSena Votes: " + shivsenaVotes);
    }

    private void declareResult() {
        int maxVotes = Math.max(bjpVotes, Math.max(incVotes, Math.max(aapVotes, Math.max(ljpVotes, shivsenaVotes))));
        String winningParty = "";
        
        if (maxVotes == bjpVotes) {
            winningParty = "BJP";
        } else if (maxVotes == incVotes) {
            winningParty = "INC";
        } else if (maxVotes == aapVotes) {
            winningParty = "AAP";
        } else if (maxVotes == ljpVotes) {
            winningParty = "LJP";
        } else if (maxVotes == shivsenaVotes) {
            winningParty = "ShivSena";
        }
        
        JOptionPane.showMessageDialog(null, "Winning Party: " + winningParty + " with " + maxVotes + " votes.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnlineVotingApp app = new OnlineVotingApp();
            app.setVisible(true);
        });
    }
}



















