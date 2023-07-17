package org.F1Soft.listners;

import org.F1Soft.Helpers.EmailHelper;
import org.F1Soft.Helpers.Log;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
public class ReportListner implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//        IReporter.super.generateReport(xmlSuites,suites,outputDirectory);

        try {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           EmailHelper.sendMail();
        } catch (MessagingException e) {
            Log.error(e.getMessage());
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
    }
}
