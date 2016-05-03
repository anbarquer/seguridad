// Seguridad en Redes Telemáticas - Práctica 4
// Antonio Diego Barquero Cuadrado
// Antonio Manuel Mateos Cruz

package header;
import java.io.*;

public class HashFileHeader {

	private final static byte MARK[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 9 };
	private final static int MARKLENGTH = 10;
	private final static int HEADERLENGTH = 11;
	private final static String algorithms[] = { "SHA1withRSA", "MD2withRSA",
			"MD5withRSA" };
	private String algorithm;
	private byte[] hash;

	public HashFileHeader() {
		algorithm = algorithms[0];
	}

	public HashFileHeader(String algorithm) {
		this.algorithm = algorithm;
	}

	public HashFileHeader(String algorithm, byte[] hash) {
		this.algorithm = algorithm;
		this.hash = hash;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public byte[] getHash() {
		return hash;
	}

	public boolean load(InputStream r) {
		byte buf[] = new byte[HEADERLENGTH];
		boolean breturn = false;
		try {
			if (r.read(buf, 0, HEADERLENGTH) == HEADERLENGTH) {
				byte i = 0;
				while ((i < MARKLENGTH) && (buf[i] == MARK[i])) {
					i++;
				}
				if (i == MARKLENGTH) {
					algorithm = algorithms[buf[i]];
					ObjectInputStream ois = new ObjectInputStream(r);
					hash = (byte[]) ois.readObject();
					breturn = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breturn;
	}

	public boolean save(OutputStream fos) {
		boolean breturn = false;
		try {
			fos.write(MARK);
			fos.write(search(algorithms, algorithm));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(hash);
			breturn = true;
		} catch (Exception e) {
		}
		return breturn;
	}

	private static int search(String a[], String m) {
		int i;
		for (i = a.length - 1; i != -1; i--) {
			if (a[i].compareTo(m) == 0) {
				break;
			}
		}
		return i;
	}

}