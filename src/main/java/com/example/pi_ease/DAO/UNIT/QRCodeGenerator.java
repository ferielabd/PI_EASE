package com.example.pi_ease.DAO.UNIT;

import com.example.pi_ease.DAO.Entities.Checks;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.var;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    public static void generateQRCode(Checks checks) throws IOException, WriterException {
        String qrCodePath ="C:\\Users\\QRCode";
        String qrCodeName = qrCodePath+checks.getIdC()+checks.getNumC()+"-QRCODE.png";
        var qrCodeWriter =new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode("ID: "+checks.getIdC()+ "\n"+
                "Numéro: "+checks.getNumC()+"\n"
                , BarcodeFormat.QR_CODE,400,400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);


    }
}
