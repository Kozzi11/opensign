/* Copyright  (c) 2002 Graz University of Technology. All rights reserved.
 *
 * Redistribution and use in  source and binary forms, with or without 
 * modification, are permitted  provided that the following conditions are met:
 *
 * 1. Redistributions of  source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in  binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment:
 * 
 *    "This product includes software developed by IAIK of Graz University of
 *     Technology."
 * 
 *    Alternately, this acknowledgment may appear in the software itself, if 
 *    and wherever such third-party acknowledgments normally appear.
 *  
 * 4. The names "Graz University of Technology" and "IAIK of Graz University of
 *    Technology" must not be used to endorse or promote products derived from 
 *    this software without prior written permission.
 *  
 * 5. Products derived from this software may not be called 
 *    "IAIK PKCS Wrapper", nor may "IAIK" appear in their name, without prior 
 *    written permission of Graz University of Technology.
 *  
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

package iaik.pkcs.pkcs11.wrapper;



/**
 * class CK_SKIPJACK_PRIVATE_WRAP_PARAMS provides the parameters to the
 * CKM_SKIPJACK_PRIVATE_WRAP  mechanism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_SKIPJACK_PRIVATE_WRAP_PARAMS {
 *    CK_ULONG ulPasswordLen;
 *    CK_BYTE_PTR pPassword;
 *    CK_ULONG ulPublicDataLen;
 *    CK_BYTE_PTR pPublicData;
 *    CK_ULONG ulPandGLen;
 *    CK_ULONG ulQLen;
 *    CK_ULONG ulRandomLen;
 *    CK_BYTE_PTR pRandomA;
 *    CK_BYTE_PTR pPrimeP;
 *    CK_BYTE_PTR pBaseG;
 *    CK_BYTE_PTR pSubprimeQ;
 *  } CK_SKIPJACK_PRIVATE_WRAP_PARAMS;
 * </PRE>
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 * @author Martin Schlaffer <schlaeff@sbox.tugraz.at>
 */
public class CK_SKIPJACK_PRIVATE_WRAP_PARAMS {

  /**
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pPassword;
   *   CK_ULONG ulPasswordLen;
   * </PRE>
   */
  public byte[] pPassword;

  /**
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pPublicData;
   *   CK_ULONG ulPublicDataLen;
   * </PRE>
   */
  public byte[]   pPublicData;

  /**
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pRandomA;
   *   CK_ULONG ulRandomLen;
   * </PRE>
   */
  public byte[] pRandomA;

  /**
   * ulPAndGLen == pPrimeP.length == pBaseG.length<p>
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pPrimeP;
   *   CK_ULONG ulPAndGLen;
   * </PRE>
   */
  public byte[] pPrimeP;

  /**
   * ulPAndGLen == pPrimeP.length == pBaseG.length
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pBaseG;
   *   CK_ULONG ulRandomLen;
   * </PRE>
   */
  public byte[] pBaseG;

  /**
   * <B>PKCS#11:</B>
   * <PRE>
   *   CK_BYTE_PTR pSubprimeQ;
   *   CK_ULONG ulQLen;
   * </PRE>
   */
  public byte[] pSubprimeQ;

  /**
   * Returns the string representation of CK_SKIPJACK_PRIVATE_WRAP_PARAMS.
   *
   * @return the string representation of CK_SKIPJACK_PRIVATE_WRAP_PARAMS
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();

    buffer.append(Constants.INDENT);
    buffer.append("ulPasswordLen: ");
    buffer.append(pPassword.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPassword: ");
    buffer.append(Functions.toHexString(pPassword));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("ulPublicDataLen: ");
    buffer.append(pPublicData.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPublicData: ");
    buffer.append(Functions.toHexString(pPublicData));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("ulPAndGLen: ");
    buffer.append(pPrimeP.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("ulQLen: ");
    buffer.append(pSubprimeQ.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("ulRandomLen: ");
    buffer.append(pRandomA.length);
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pRandomA: ");
    buffer.append(Functions.toHexString(pRandomA));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pPrimeP: ");
    buffer.append(Functions.toHexString(pPrimeP));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pBaseG: ");
    buffer.append(Functions.toHexString(pBaseG));
    buffer.append(Constants.NEWLINE);

    buffer.append(Constants.INDENT);
    buffer.append("pSubprimeQ: ");
    buffer.append(Functions.toHexString(pSubprimeQ));
    //buffer.append(Constants.NEWLINE);

    return buffer.toString();
  }

}
