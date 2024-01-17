// this program does not show the timer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TimerApp extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private int seconds;
    private int milliseconds;

    public TimerApp() {
        setTitle("Stop at 10.0 Microseconds Timer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        timerLabel = new JLabel("Timer: 0.0s");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        timerLabel.setForeground(Color.RED);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milliseconds += 100;
                if (milliseconds >= 1000) {
                    milliseconds = 0;
                    seconds++;
                }

                timerLabel.setText("Timer: " + seconds + "." + new DecimalFormat("0.#").format(milliseconds / 100.0) + "s");
                
                if (seconds == 11) {
                    resetTimer();
                }
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                String message;
                if (seconds == 10 && milliseconds == 0) {
                    message = "Congratulations! You stopped at " + seconds + " seconds " + new DecimalFormat("0.#").format(milliseconds / 100.0) + " milliseconds. Play again?";
                } else {
                    message = "Good! Almost there. You stopped at " + seconds + " seconds " + new DecimalFormat("0.#").format(milliseconds / 100.0) + " milliseconds. Try again?";
                }

                int option = JOptionPane.showConfirmDialog(null, message, "Result", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    resetTimer();
                } else {
                    System.exit(0);
                }
            }
        });

        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(stopButton);
        buttonPanel.add(replayButton);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Commenting out the line below will remove the timer heading label
        // mainPanel.add(timerLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        setVisible(true);

        // Add a small delay (0 milliseconds) before starting the timer
        Timer startTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        startTimer.setRepeats(false);
        startTimer.start();
    }

    private void resetTimer() {
        seconds = 0;
        milliseconds = 0;
        timerLabel.setText("Timer: 0.0s");
        timerLabel.setForeground(Color.RED);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimerApp());
    }
}

