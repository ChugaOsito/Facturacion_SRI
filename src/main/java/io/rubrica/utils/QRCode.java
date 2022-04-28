/*
 * Copyright (C) 2020 
 * Authors: Ricardo Arguello, Misael Fernández
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.*
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.rubrica.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.EnumMap;

/**
 * Created by gustavo.peiretti on 14/09/2015.
 * http://gustavopeiretti.com/java-generar-codigo-qr/
 * https://github.com/zxing/zxing
 */
public class QRCode {

    public static void main(String[] args) {

        QRCode qr = new QRCode();
        File file = new File("qrCode.png");
        String text = "Nombre firmante: MISAEL VLADIMIR FERNANDEZ CORREA\n"
                + "Razón: Firmado digitalmente con RUBRICA\n" + "Fecha firmado: 2018-05-31T11:39:47.247-05:00\n"
                + "Firmado digitalmente con RUBRICA\n" + "https://minka.gob.ec/rubrica/rubrica";

        try {

            java.awt.image.BufferedImage bufferedImage = qr.generateQR(text, 300, 300);
            ImageIO.write(bufferedImage, "png", file);
            System.out.println("QRCode Generated: " + file.getAbsolutePath());

            String qrString = qr.decoder(file);
            System.out.println("Text QRCode: " + qrString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage generateQR(String text, int h, int w) throws Exception {
        // Generamos el mapa de caracterìsticas que requerimos para el QR
        java.util.Map<com.google.zxing.EncodeHintType, Object> hints = new EnumMap<>(
                com.google.zxing.EncodeHintType.class);
        // En nuestro caso particular agregamos soporte para el español con la
        // codificación ISO-8859-1
        hints.put(com.google.zxing.EncodeHintType.CHARACTER_SET, java.nio.charset.StandardCharsets.US_ASCII.name());// ISO_8859_1
        // Desde la versión 3.2.1 de Zxing podemos establecer el tamaño del borde, por
        // default es 4
        hints.put(com.google.zxing.EncodeHintType.MARGIN, 0);
        // Agregamos la correción de error del QR
        hints.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION,
                com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, w, h, hints);

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }

    public static String decoder(File file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // decode the barcode
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return new String(result.getText());
    }
}
