public String loginToClient() throws FileNotFoundException, IOException {
        //decryptUsers();
        int tries;
        tries = 5;
        while (tries > 0) {
            System.out.println("LOGIN");
            String usnm = c.readLine("Username: ");
            char [] passwd = c.readPassword("Password: ");
            users = new FileInputStream("users.fra");
            DataInputStream dis = new DataInputStream(users);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String logindat = br.readLine();
            System.out.println(logindat);
            if (logindat.contains(usnm) == null) {
                System.err.println("Username not recognised, please try another or create user.");
                usnm = "INV";
                return usnm;
            }
            else {
                int startUsnm = logindat.indexOf(usnm);
                System.out.println("startUsnm: " + startUsnm);
                String logdat = logindat.substring(startUsnm, logindat.indexOf("."));
                System.out.println("logdat: " + logdat);
                int endUsnm = logdat.indexOf(':'); 
                System.out.println("endUsnm: " + endUsnm);
                int usnmend = endUsnm - 1;
                System.out.println("usnmend: " + usnmend);
                int startPass = endUsnm + 1;
                System.out.println("startPass: " + startPass);
                int endPass = logdat.indexOf('.');
                System.out.println("endPass: " + endPass);
                String Usnm = logdat.substring(0, usnmend);
                System.out.println("Usnm: " + Usnm);
                int passend = endPass - 1;
                System.out.println("passend: " + passend);
                String Pass = logdat.substring(startPass, passend);
                System.out.println("Pass: " + Pass);
                char [] Passwd = Pass.toCharArray();
                if (usnm.equals(Usnm)) {
                    if (Arrays.equals(passwd,Passwd)) {
                        System.out.println ("Logged in. Welcome, " + usnm + ".");
                        String data = "LOGIN: " + usnm;
                        printLog(data);
                        //encryptUsers();
                        return usnm;
                    }
                    else {
                        System.out.println ("Incorrect password, please try again.");
                        String data = "PASWFAIL: " + usnm;
                        printLog(data);
                        tries -= 1;
                    }
                }
                else {
                    System.out.println ("Username not recognised.");
                    printLog("USNAMFAIL");
                    usnm = "INV";
                    return usnm;
                    //encrytUsers();
                }
            }
        }
        //encryptUsers();
        System.exit(2);
        return usnm;
    }