import java.math.BigInteger;
import java.util.Scanner;

public class menu {

    public void run() {
        RSAsim rsaSimulation = new RSAsim();
        BigInteger publicKey = rsaSimulation.getPublicKey();
        BigInteger privateKey = rsaSimulation.getPrivateKey();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our RSA Algrotihm system!");
        System.out.println("---------------------------------");


        System.out.println("Your public key is: " + publicKey);
        System.out.println("---------------------------------");
        //generally would not show priv key but for testing purposes we decdied to
        System.out.println("Your private key is: " + privateKey);
        System.out.println("---------------------------------");

        // Alice sends a message to Bob
        System.out.println("Alice, please enter your message:");
        String message = scanner.nextLine();
        System.out.println("---------------------------------");

        BigInteger ciphertext = rsaSimulation.encrypt(message);


        //        //error in the ciphertext causes code to crash and bob to unsuccssefully decrypt the ciphertext
        //      ciphertext = ciphertext.flipBit(3); // flip the 3rd bit

        System.out.println("Alice's ciphertext: " + ciphertext);
        System.out.println("Sending message to Bob...");
        System.out.println("---------------------------------");

        // Charlie intercepts the message
        BigInteger interceptedCiphertext = ciphertext;
        System.out.println("Hacker Charlie intercepted the message!");
        System.out.println("Intercepted ciphertext: " + interceptedCiphertext);
        System.out.println("---------------------------------");


        // Bob receives the message and decrypts it
        String plaintext = rsaSimulation.decrypt(interceptedCiphertext);
        System.out.println("Bob received the ciphertext: " + interceptedCiphertext);
        System.out.println("Bob's plaintext: " + plaintext);
        System.out.println("---------------------------------");



        // Check if Bob successfully decrypted the message
        if (plaintext.equals(message)) {
            System.out.println("Bob successfully decrypted the message");
        } else {
            System.out.println("Bob failed to decrypt the message.");
        }


        // Charlie sees the plaintext
        System.out.println("---------------------------------");
        System.out.println("Hacker Charlie sees the plaintext: " + plaintext);
    }

    public static void main(String[] args) {
        menu menu = new menu();
        menu.run();
    }
}
