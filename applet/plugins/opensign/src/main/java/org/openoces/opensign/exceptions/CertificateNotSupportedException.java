package org.openoces.opensign.exceptions;

public class CertificateNotSupportedException extends Exception {
	private static final long serialVersionUID = 7336580819290800184L;

	public CertificateNotSupportedException() {
    }

    public CertificateNotSupportedException(String message) {
        super(message);
    }

    public CertificateNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateNotSupportedException(Throwable cause) {
        super(cause);
    }
}
