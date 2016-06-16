package com.mds.passkit.manager.impl;

import com.mds.passkit.constants.PasskitConstants;
import com.mds.passkit.exception.PasskitException;
import com.mds.passkit.manager.PassKitManager;
import com.ryantenney.passkit4j.Pass;
import com.ryantenney.passkit4j.PassSerializer;
import com.ryantenney.passkit4j.sign.PassSigner;
import com.ryantenney.passkit4j.sign.PassSignerImpl;
import com.ryantenney.passkit4j.sign.PassSigningException;

import java.io.*;

/*
 * Pass generation pkpass file and sign for deployment on Wallet App
 */
public class IOSPassKitManager implements PassKitManager,PasskitConstants {

    @Override
    public void generate(Pass pass,String fileName,String p12Certificates,String appleWWDRCA,String password,String alias) throws PasskitException {
        try {
                PassSigner signer = getPassSigner(p12Certificates,appleWWDRCA,password,alias);

                PassSerializer.writePkPassArchive(pass, signer, new FileOutputStream(fileName));
        }
        catch (Exception e)
        {
          throw new PasskitException(e);
        }
    }


    PassSigner getPassSigner(String p12Certificate,String appleWWDRCA,String password,String alias) throws PasskitException
    {
        try {
            PassSigner signer = PassSignerImpl.builder()
                    .keystore(new FileInputStream(p12Certificate), password)
                    .signingCertificateAlias(alias)
                    .privateKeyAlias(alias)
                    .intermediateCertificate(new FileInputStream(appleWWDRCA))
                    .build();
            return signer;
        }catch (Exception e)
        {
            throw new PasskitException(e);
        }
    }


}
