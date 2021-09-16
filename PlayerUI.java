import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Thanasis, Aris
 */
public class PlayerUI {

    private JFrame gameFrame;
    private JPanel gamePanel;
    private JLabel startLabel;
    private JLabel enterLabel;
    private JLabel gameModeLabel;
    private JLabel roundLabel;
    private JLabel categoryLabel;
    private JLabel imageLabel;
    private JLabel questionLabel;
    private JLabel resultLabel;
    private JRadioButton ans1;
    private JRadioButton ans2;
    private JRadioButton ans3;
    private JRadioButton ans4;
    private JTextField nameText;
    private JButton onePlayerButton;
    private JButton twoPlayersButton;
    private JButton goButton;
    private JButton nextQuestionButton;
    private JButton correctAnswerButton;
    private JButton betButton;
    private JButton stopTheClockButton;
    private ButtonGroup answersGroup;
    private Player player;
    private ArrayList<Category> categories;
    private Question question;
    private int answeredQuestions;
    private int round;
    private Instant start;
    private Instant finish;

    public PlayerUI(ArrayList<Category> categories) {

        gameFrame = new JFrame("Buzz quiz!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setSize(600, 500);
        gameFrame.setFocusable(true);

        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gameFrame.add(gamePanel);

        startLabel = new JLabel("Hello, let's play Buzz!");
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(startLabel);

        onePlayerButton = new JButton("1 Player");
        onePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(onePlayerButton);

        twoPlayersButton = new JButton("2 Players");
        twoPlayersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(twoPlayersButton);

        ActionListener onePlayerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onePlayerButton.setVisible(false);
                twoPlayersButton.setVisible(false);

                enterLabel = new JLabel("Enter your nickname: ");
                enterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                gamePanel.add(enterLabel);

                nameText = new JTextField(30);
                nameText.setMaximumSize(nameText.getPreferredSize());
                nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
                gamePanel.add(nameText);

                goButton = new JButton("GO");
                goButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                gamePanel.add(goButton);

                gameModeLabel = new JLabel();
                gameModeLabel.setVisible(false);
                gamePanel.add(gameModeLabel);

                correctAnswerButton = new JButton("Correct Answer");
                correctAnswerButton.setVisible(false);
                gamePanel.add(correctAnswerButton);

                betButton = new JButton("Bet");
                betButton.setVisible(false);
                gamePanel.add(betButton);

                stopTheClockButton = new JButton("Stop The Clock");
                stopTheClockButton.setVisible(false);
                gamePanel.add(stopTheClockButton);

                ActionListener goListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startLabel.setVisible(false);
                        enterLabel.setVisible(false);
                        nameText.setVisible(false);
                        goButton.setVisible(false);

                        player = new Player(nameText.getText(), 0);

                        gameModeLabel.setText("Hi " + nameText.getText() + "! What do you want to play?");
                        gameModeLabel.setVisible(true);
                        gameModeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        correctAnswerButton.setVisible(true);
                        correctAnswerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                        betButton.setVisible(true);
                        betButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                        stopTheClockButton.setVisible(true);
                        stopTheClockButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                        correctAnswerButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                correctAnswer();
                            }
                        });

                        betButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bet();
                            }
                        });

                        stopTheClockButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                stopTheClock();
                            }
                        });
                    }
                };
                goButton.addActionListener(goListener);
                nameText.addActionListener(goListener);
            }
        };
        onePlayerButton.addActionListener(onePlayerListener);

        ActionListener twoPlayersListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                TwoPlayersUI ui = new TwoPlayersUI(categories);
                ui.start();
            }
        };
        twoPlayersButton.addActionListener(twoPlayersListener);

        roundLabel = new JLabel();
        roundLabel.setVisible(false);
        gamePanel.add(roundLabel);

        categoryLabel = new JLabel();
        categoryLabel.setVisible(false);
        gamePanel.add(categoryLabel);

        imageLabel = new JLabel();
        imageLabel.setVisible(false);
        gamePanel.add(imageLabel);

        questionLabel = new JLabel();
        questionLabel.setVisible(false);
        gamePanel.add(questionLabel);

        ans1 = new JRadioButton();
        ans1.setVisible(false);
        gamePanel.add(ans1);

        ans2 = new JRadioButton();
        ans2.setVisible(false);
        gamePanel.add(ans2);

        ans3 = new JRadioButton();
        ans3.setVisible(false);
        gamePanel.add(ans3);

        ans4 = new JRadioButton();
        ans4.setVisible(false);
        gamePanel.add(ans4);

        nextQuestionButton = new JButton("NEXT");
        nextQuestionButton.setVisible(false);
        gamePanel.add(nextQuestionButton);

        resultLabel = new JLabel();
        resultLabel.setVisible(false);
        gamePanel.add(resultLabel);

        answersGroup = new ButtonGroup();

        this.categories = categories;
        this.answeredQuestions = 0;
        this.round = 0;
    }

    public void start() {
        gameFrame.setVisible(true);
    }

    private void initGameComponents() {
        gameModeLabel.setVisible(false);
        correctAnswerButton.setVisible(false);
        betButton.setVisible(false);
        stopTheClockButton.setVisible(false);
        roundLabel.setVisible(true);
        categoryLabel.setVisible(true);
        questionLabel.setVisible(true);
        resultLabel.setVisible(true);
        ans1.setVisible(true);
        ans2.setVisible(true);
        ans3.setVisible(true);
        ans4.setVisible(true);
        nextQuestionButton.setVisible(true);
        answersGroup.add(ans1);
        answersGroup.add(ans2);
        answersGroup.add(ans3);
        answersGroup.add(ans4);
    }

    private void setQuestion() {
        answersGroup.clearSelection();
        nextQuestionButton.setEnabled(false);
        if (answeredQuestions%3 == 0) {
            round++;
        }
        if (round <= 4) {
            roundLabel.setText("Round " + (round));
            Random rand = new Random();
            Category category = categories.get(rand.nextInt(6));
            category.setIndex();
            question = category.getQuestion();
            categoryLabel.setText(category.getCategoryName());
            questionLabel.setText(question.getTitle());
            if (question.getHasImage()) {
                imageLabel.setVisible(true);
                imageLabel.setIcon(new ImageIcon(question.getImageFilename()));
            }

            setAnswer();
        } else {
            finishGame();
        }
    }

    private void setAnswer() {
        ArrayList<Answer> answers = question.getAnswers();
        ans1.setText(answers.get(0).getAns());
        ans2.setText(answers.get(1).getAns());
        ans3.setText(answers.get(2).getAns());
        ans4.setText(answers.get(3).getAns());
    }

    private void finishGame() {
        ResultsFile resultsFile = new ResultsFile("results.txt");
        resultsFile.setPlayerResults(player, 1);
        JOptionPane.showMessageDialog(gameFrame, "The game is over.\nYour score is: " + player.getScore() + " points!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(gameFrame, player.getName() + "\nHigh Score: " + player.getHighScore() + " points.\nWins: " + player.getWins(), "Player Stats", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void correctAnswer() {
        initGameComponents();

        nextQuestionCorrectAnswer();
        setUpListenersCorrectAnswer();
    }

    private void nextQuestionCorrectAnswer() {
        setQuestion();
    }

    private void setUpListenersCorrectAnswer() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                int selectedAnswer = 0;
                if (ans1.isSelected()) {
                    selectedAnswer = 1;
                } else if (ans2.isSelected()) {
                    selectedAnswer = 2;
                } else if (ans3.isSelected()) {
                    selectedAnswer = 3;
                } else if (ans4.isSelected()) {
                    selectedAnswer = 4;
                }
                resultLabel.setText(player.correctAnswer(question.checkQuestion(selectedAnswer), question.getRightAns()));
                imageLabel.setVisible(false);
                nextQuestionCorrectAnswer();
            }
        });

        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });

        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans1.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '2':
                        ans2.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '3':
                        ans3.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '4':
                        ans4.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                }
            }
        });
    }

    private void bet() {
        initGameComponents();

        nextQuestionBet();
        setUpListenersBet();
    }

    private void nextQuestionBet() {
        answersGroup.clearSelection();
        nextQuestionButton.setEnabled(false);
        if (answeredQuestions % 3 == 0) {
            round++;
        }
        if (round <= 4) {
            roundLabel.setText("Round " + (round));
            Random rand = new Random();
            Category category = categories.get(rand.nextInt(6));
            category.setIndex();
            question = category.getQuestion();
            if (question.getHasImage()) {
                imageLabel.setVisible(true);
                imageLabel.setIcon(new ImageIcon(question.getImageFilename()));
            }

            Integer[] bets = {250,500,750,1000};
            int bet = (Integer) JOptionPane.showInputDialog(gamePanel,"Category: " + category.getCategoryName(),"Bet",JOptionPane.PLAIN_MESSAGE,null,bets,bets[0]);
            player.placeBet(bet);

            categoryLabel.setText(category.getCategoryName());
            questionLabel.setText(question.getTitle());

            setAnswer();
        } else {
            finishGame();
        }

    }

    private void setUpListenersBet() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                int selectedAnswer = 0;
                if (ans1.isSelected()) {
                    selectedAnswer = 1;
                } else if (ans2.isSelected()) {
                    selectedAnswer = 2;
                } else if (ans3.isSelected()) {
                    selectedAnswer = 3;
                } else if (ans4.isSelected()) {
                    selectedAnswer = 4;
                }
                resultLabel.setText(player.Bet(question.checkQuestion(selectedAnswer), question.getRightAns()));
                imageLabel.setVisible(false);
                nextQuestionBet();
            }
        });

        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });
        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestionButton.setEnabled(true);
            }
        });

        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans1.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '2':
                        ans2.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '3':
                        ans3.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '4':
                        ans4.setSelected(true);
                        nextQuestionButton.setEnabled(true);
                        break;
                }
            }
        });
    }

    private void stopTheClock() {
        initGameComponents();

        nextQuestionStopTheClock();
        setUpListenersStopTheClock();
    }

    private void nextQuestionStopTheClock() {
        setQuestion();
        start = Instant.now();
    }

    private void setUpListenersStopTheClock() {
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answeredQuestions++;
                int selectedAnswer = 0;
                if (ans1.isSelected()) {
                    selectedAnswer = 1;
                } else if (ans2.isSelected()) {
                    selectedAnswer = 2;
                } else if (ans3.isSelected()) {
                    selectedAnswer = 3;
                } else if (ans4.isSelected()) {
                    selectedAnswer = 4;
                }
                if (Duration.between(start, finish).toMillis() > 5000) {
                    resultLabel.setText("To late! Your score so far is: " + player.getScore());
                }
                else {
                    resultLabel.setText(player.stopTheClock(question.checkQuestion(selectedAnswer), 5000 - Duration.between(start, finish).toMillis(), question.getRightAns()));
                }
                imageLabel.setVisible(false);
                nextQuestionStopTheClock();
            }
        });

        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish = Instant.now();
                nextQuestionButton.setEnabled(true);
            }
        });
        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish = Instant.now();
                nextQuestionButton.setEnabled(true);
            }
        });
        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish = Instant.now();
                nextQuestionButton.setEnabled(true);
            }
        });
        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish = Instant.now();
                nextQuestionButton.setEnabled(true);
            }
        });

        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case '1':
                        ans1.setSelected(true);
                        finish = Instant.now();
                        nextQuestionButton.setEnabled(true);
                        break;
                    case '2':
                        finish = Instant.now();
                        ans2.setSelected(true);
                        break;
                    case '3':
                        finish = Instant.now();
                        ans3.setSelected(true);
                        break;
                    case '4':
                        finish = Instant.now();
                        ans4.setSelected(true);
                        break;
                }
            }
        });
    }
}
