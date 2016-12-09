package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import TileEngine.Map;
import TileEngine.TileLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class GuiGame extends javax.swing.JFrame {
    
    // Udp connection variables
	Boolean found = false;
	DatagramSocket c;
	byte[] sendData;
	byte[] recvBuf;
	DatagramPacket sendPacket;
	DatagramPacket receivePacket;
	String message;
	// Tcp connection variables
	Boolean isConnected = false;
	Boolean isRegistred = false;
	Boolean isJoined = false;
	Socket socket;
	String ipAddr;
	BufferedReader in;
	//PrintStream out;
	OutputStreamWriter out;
	BufferedWriter bw;
	Map frame;
    int nbRows = 0;
    int nbCols = 0;
    char[][] map;
    int i = 0;

        // Variable where server ip @ is saved
	private Object serverAddress;

	/**
	 * This method is called to get the server ip @
	 * 
	 * @return
	 */
	public Object getServerAddress() {
		return serverAddress;
	}

	/**
	 * This methode is called to save the server ip @
	 * 
	 * @param serverAddress
	 */
	public void setServerAddress(Object serverAddress) {
		this.serverAddress = serverAddress;
	}
	

    /**
     * Creates new form Gui
     */
    public GuiGame() {
        initComponents();
    }
    public synchronized void ListenThread() {
		Thread IncomingReader = new Thread(new IncomingReader());
		IncomingReader.start();
	}

    public class IncomingReader implements Runnable {
    String response;
    String[] data;
    String[] nmap;
    String[] nbr = new String[3];
    String[] nbc = new String[3];

	@Override
    public synchronized void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name+"started");
    try {
    while ((response = in.readLine()) != null) {
        if (response.contains("map")) {
            data = response.split(" ");
            nbr = data[1].split("-");
            nbc = data[2].split("-");
            nbRows = Integer.parseInt(nbr[2]);
            nbCols = Integer.parseInt(nbc[2]);
            map = new char[nbRows][nbCols];
            jTextAreaConsol.append(response+"\n");
        }else if(response.contains("number")) {
            jTextAreaConsol.append(response+"\n");
        }else if (response.contains("created")) {
            jTextAreaConsol.append(response+"\n");
        }else if (response.contains("players")) {
            data = response.split(" ");
            nmap = data[1].split("-");
             jComboBoxGameList.addItem(nmap[2]);
            jTextAreaConsol.append(response+"\n");
        }else {
        	for (int j = 0; j < nbCols; j++) {
				map[i][j] = (response.charAt(j));
			}
        	i++;
        	if(i == nbRows) {
        		frame = new Map(map, nbRows, nbCols);
        	}
        }
    }
    } catch (IOException ex) {
            Logger.getLogger(GuiGame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanelMap = new javax.swing.JPanel();
        jPanelChatOutput = new javax.swing.JPanel();
        jScrollPaneChatOutput = new javax.swing.JScrollPane();
        jTextAreaChatOutput = new javax.swing.JTextArea();
        jPanelConnection = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonFindServer = new javax.swing.JButton();
        jComboBoxServerList = new javax.swing.JComboBox<>();
        jButtonConnect = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonRegister = new javax.swing.JButton();
        jTextFieldPlayerName = new javax.swing.JTextField();
        jComboBoxGameList = new javax.swing.JComboBox<>();
        jButtonJoin = new javax.swing.JButton();
        jButtonCreateGame = new javax.swing.JButton();
        jTextFieldGameName = new javax.swing.JTextField();
        jButtonRefresh = new javax.swing.JButton();
        jButtonDisconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsol = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanelChatInput = new javax.swing.JPanel();
        jTextFieldMessage = new javax.swing.JTextField();
        jButtonSendMessage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setSize(new java.awt.Dimension(1200, 800));

        jPanelMap.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanelMap.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelMapLayout = new javax.swing.GroupLayout(jPanelMap);
        jPanelMap.setLayout(jPanelMapLayout);
        jPanelMapLayout.setHorizontalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelMapLayout.setVerticalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanelChatOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jTextAreaChatOutput.setEditable(false);
        jTextAreaChatOutput.setColumns(20);
        jTextAreaChatOutput.setRows(5);
        jScrollPaneChatOutput.setViewportView(jTextAreaChatOutput);

        javax.swing.GroupLayout jPanelChatOutputLayout = new javax.swing.GroupLayout(jPanelChatOutput);
        jPanelChatOutput.setLayout(jPanelChatOutputLayout);
        jPanelChatOutputLayout.setHorizontalGroup(
            jPanelChatOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneChatOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
        );
        jPanelChatOutputLayout.setVerticalGroup(
            jPanelChatOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChatOutputLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPaneChatOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelConnection.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Connection", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Server list");

        jButtonFindServer.setText("Find server");
        jButtonFindServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindServerActionPerformed(evt);
            }
        });

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Game list");

        jButtonRegister.setText("Register");
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        jTextFieldPlayerName.setText("Player name");
        jTextFieldPlayerName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldPlayerNameMouseClicked(evt);
            }
        });

        jButtonJoin.setText("Join");
        jButtonJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJoinActionPerformed(evt);
            }
        });

        jButtonCreateGame.setText("Create game");
        jButtonCreateGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateGameActionPerformed(evt);
            }
        });

        jTextFieldGameName.setText("Game name");
        jTextFieldGameName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldGameNameMouseClicked(evt);
            }
        });

        jButtonRefresh.setText("Refresh game list");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonDisconnect.setText("Disconnect");

        jTextAreaConsol.setEditable(false);
        jTextAreaConsol.setColumns(20);
        jTextAreaConsol.setRows(5);
        jScrollPane1.setViewportView(jTextAreaConsol);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Consol");

        javax.swing.GroupLayout jPanelConnectionLayout = new javax.swing.GroupLayout(jPanelConnection);
        jPanelConnection.setLayout(jPanelConnectionLayout);
        jPanelConnectionLayout.setHorizontalGroup(
            jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelConnectionLayout.createSequentialGroup()
                        .addComponent(jButtonFindServer, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addGap(23, 23, 23)
                        .addComponent(jComboBoxServerList, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConnectionLayout.createSequentialGroup()
                        .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConnectionLayout.createSequentialGroup()
                                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(23, 23, 23))
                            .addGroup(jPanelConnectionLayout.createSequentialGroup()
                                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jButtonCreateGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxGameList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPlayerName)
                            .addComponent(jTextFieldGameName)
                            .addComponent(jButtonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(jPanelConnectionLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelConnectionLayout.setVerticalGroup(
            jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFindServer)
                    .addComponent(jComboBoxServerList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegister)
                    .addComponent(jTextFieldPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonConnect)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGameList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonJoin))
                .addGap(18, 18, 18)
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreateGame)
                    .addComponent(jTextFieldGameName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRefresh)
                    .addComponent(jButtonDisconnect))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelChatInputLayout = new javax.swing.GroupLayout(jPanelChatInput);
        jPanelChatInput.setLayout(jPanelChatInputLayout);
        jPanelChatInputLayout.setHorizontalGroup(
            jPanelChatInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChatInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldMessage)
                .addContainerGap())
        );
        jPanelChatInputLayout.setVerticalGroup(
            jPanelChatInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChatInputLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonSendMessage.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelChatOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelChatInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelChatOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelChatInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonSendMessage))))
                    .addComponent(jPanelConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButtonFindServerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        try {
			c = new DatagramSocket();
			c.setBroadcast(true);
			
			String command = "looking for poketudiant server";
			sendData = command.getBytes();
			// Try the 255.255.255.255 first
			try {
				sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"),
						9000);
				c.send(sendPacket);
				jTextAreaConsol.append("> Request packet sent to: 255.255.255.255 (DEFAULT)\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Broadcast the command to all the network interfaces
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();
				if (networkInterface.isLoopback() || !networkInterface.isUp()) {
					// Don't want to broadcast to the loopback interface
					continue;
				}
				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					InetAddress broadcast = interfaceAddress.getBroadcast();
					if (broadcast == null) {
						continue;
					}
					// Send the broadcast package !
					try {
						sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 9000);
						c.send(sendPacket);
					} catch (Exception e) {
						e.printStackTrace();
					}
					jTextAreaConsol.append("> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: "
							+ networkInterface.getDisplayName() + "\n");
				}
			}
			jTextAreaConsol.append("> Done looping over all network interfaces. Now waiting for a reply!\n");
			// Wait for a reponse
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// This will run on a separate thread
						recvBuf = new byte[15000];
						DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
						c.receive(receivePacket);
						// We have a response
						jTextAreaConsol.append("> Broadcast response from server: "
								+ receivePacket.getAddress().getHostAddress() + "\n");
						// Check if the message is correct
						message = new String(receivePacket.getData()).trim();
						if (message.equals("i am a poketudiant server")) {
							// Save the server ip @
							setServerAddress(receivePacket.getAddress().getHostAddress());
							jComboBoxServerList.addItem((String) getServerAddress());
							found = true;
							// Close the receive socket
							c.close();
						}
					} catch (IOException ex) {
						Logger.getLogger(GuiGame.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			});
			// start the thread
			thread.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }                                                 

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if (isRegistred == false) {
			String name = jTextFieldPlayerName.getText();
			try {
				ipAddr = (String) jComboBoxServerList.getSelectedItem();
				InetAddress server = InetAddress.getByName(ipAddr);
				socket = new Socket(server, 9001);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//out = new PrintStream(socket.getOutputStream());
				out = new OutputStreamWriter(socket.getOutputStream());
	            bw = new BufferedWriter(out);
				bw.write(name);
				bw.flush();
			} catch (IOException ex) {
				Logger.getLogger(GuiGame.class.getName()).log(Level.SEVERE, null, ex);
				//System.out.println("Cannot Connect! Try Again. \n");
				jTextAreaConsol.append("Cannot Connect! Try Again. \n");
			}
			isRegistred = true;
		} else if (isRegistred == true) {
			jTextAreaConsol.append("You are already registred. \n");
		}
    }                                               

    private synchronized void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if (isConnected == false) {
			String command = "require game list";
			try {
				bw.write(command+'\n');
				bw.flush();
			} catch (IOException e) {
				jTextAreaConsol.append("error sending command\n");
				e.printStackTrace();
			}
			ListenThread();
			isConnected = true;
		} else if (isConnected == true) {
			jTextAreaConsol.append("You are already connected. \n");
		}
    }                                              

    private synchronized void jButtonJoinActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (isJoined == false) {
			String crtGame = "join game " + jComboBoxGameList.getSelectedItem();
			try {
				bw.write(crtGame+'\n');
				bw.flush();
			} catch (IOException e) {
				jTextAreaConsol.append("error joining game\n");
				e.printStackTrace();
			}
			//ListenThread();
			isJoined = true;
		} else if (isJoined == true) {
			jTextAreaConsol.append("You are already joined the game. \n");
		}
    }                                           

    private synchronized void jButtonCreateGameActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        String crtGame = "create game " + jTextFieldGameName.getText();
		try {
			bw.write(crtGame+'\n');
			bw.flush();
		} catch (IOException e) {
			jTextAreaConsol.append("error create game\n");
			e.printStackTrace();
		}
		//ListenThread();
    }                                                 

    private synchronized void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String command = "require game list";
		try {
			bw.write(command+'\n');
			bw.flush();
		} catch (IOException e) {
			jTextAreaConsol.append("error refresh command\n");
			e.printStackTrace();
		}
		jComboBoxGameList.removeAllItems();
		//ListenThread();
    }                                              

    private void jTextFieldGameNameMouseClicked(java.awt.event.MouseEvent evt) {                                                
        jTextFieldGameName.setText("");
    }                                               

    private void jTextFieldPlayerNameMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        jTextFieldPlayerName.setText("");
    }                                                 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonCreateGame;
    private javax.swing.JButton jButtonDisconnect;
    private javax.swing.JButton jButtonFindServer;
    private javax.swing.JButton jButtonJoin;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JButton jButtonSendMessage;
    private javax.swing.JComboBox<String> jComboBoxGameList;
    private javax.swing.JComboBox<String> jComboBoxServerList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelChatInput;
    private javax.swing.JPanel jPanelChatOutput;
    private javax.swing.JPanel jPanelConnection;
    private javax.swing.JPanel jPanelMap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneChatOutput;
    private javax.swing.JTextArea jTextAreaChatOutput;
    private javax.swing.JTextArea jTextAreaConsol;
    private javax.swing.JTextField jTextFieldGameName;
    private javax.swing.JTextField jTextFieldMessage;
    private javax.swing.JTextField jTextFieldPlayerName;
    // End of variables declaration                   
}