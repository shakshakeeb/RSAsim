import java.math.BigInteger;
import java.util.Random;



public class RSAsim {
    private BigInteger p, q, n, phi, e, d;

    // Constructor method that generates RSA keys
    public RSAsim() {
        generateKeys();
    }

    // Private method to generate RSA keys
    private void generateKeys() {
        Random rand = new Random();

        // Generate two random prime numbers p and q
        p = BigInteger.probablePrime(512, rand);
        q = BigInteger.probablePrime(512, rand);

        // Calculate n = p*q and phi = (p-1)*(q-1)
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Find a random number e that is coprime with phi
        e = BigInteger.probablePrime(512, rand);
        while (!phi.gcd(e).equals(BigInteger.ONE) || e.compareTo(phi) >= 0) {
            e = BigInteger.probablePrime(512, rand);
        }

        // Calculate the modular multiplicative inverse of e mod phi to get d
        d = e.modInverse(phi); // d has private access
    }

    // Public method to get the RSA public key (e)
    public BigInteger getPublicKey() {
        return e;
    }

    // Public method to get the RSA private key (d)
    // Note: not secure to expose private key in this way, just for testing purposes
    public BigInteger getPrivateKey() {
        return d;
    }

    // Public method to encrypt a message using RSA
    public BigInteger encrypt(String message) {
        BigInteger m = new BigInteger(message.getBytes());
        return m.modPow(e, n);
    }

    // Public method to decrypt a ciphertext using RSA
    public String decrypt(BigInteger ciphertext) {
        BigInteger m = ciphertext.modPow(d, n);
        return new String(m.toByteArray());
    }
}
