package org.openoces.opensign.test.xml.xmldsig;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.client.applet.attach.Checksum;
import org.openoces.opensign.client.applet.attach.InvalidContentException;
import org.openoces.opensign.client.applet.attach.Sha1Checksum;
import org.openoces.opensign.utils.Base64;


@Ignore
public class TestAttachmentImpl implements Attachment{

	private byte[] content;
	private boolean primary;
	private String mimetype;
    private Checksum hashValue;
    private Checksum localHashValue;
    
	public TestAttachmentImpl(String fname) throws Exception {
		URL url = this.getClass().getResource("/" + fname);
		InputStream stream = url.openStream();
		content = new byte[stream.available()];
		stream.read(content);

		Assert.assertFalse("Not read all attachment bytes", stream.available() > 0);
		hashValue = new Sha1Checksum();
		hashValue.update(content, 0, content.length);
		hashValue.digest();
	}
	
	@Override
	public boolean isHasSeen() {
		return true;
	}

	@Override
	public String getHashValue() {
        return new String(Base64.encode(hashValue.getShaValue()));
	}

	@Override
	public Checksum getLocalChecksum() {
		return localHashValue;
	}

	@Override
	public boolean isPrimary() {
		return this.primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	@Override
	public boolean isOptional() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public String getMimeType() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	@Override
	public byte[] getContents() {
		return content;
	}

	@Override
	public byte[] getEncodeContents() throws InvalidContentException {
		return Base64.encode(content);
	}

	@Override
    public String getPath() {
        return null;
    }

    @Override
    public Checksum getChecksum() {
        return null;
    }

    @Override
    public void setLocalChecksum(Checksum checksum) {
    }

    @Override
    public void setChecksumVerified() {
    }

    @Override
    public String getTitle() {return "";}

}
