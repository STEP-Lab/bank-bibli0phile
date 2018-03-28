package com.thoughtworks.step.bank;

import java.io.FileWriter;
import java.io.IOException;

public class CSVPrinter{
  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  private final FileWriter writer;
  private final String headers;
  
  public CSVPrinter(FileWriter writer,String headers) {
    this.writer = writer;
    this.headers = headers;
  }
  
  public void writeHeaders() throws IOException {
    writer.append(headers);
    writer.append(NEW_LINE_SEPARATOR);
  }
  
  public void iterateOverTransactions(Transaction transaction) throws IOException {
    writer.append(transaction.getDate().toString());
    writer.append(COMMA_DELIMITER);
    if (transaction instanceof CreditTransaction){
      writer.append("credit");
    } else {
      writer.append("debit");
    }
    writer.append(COMMA_DELIMITER);
    writer.append(String.valueOf(transaction.getAmount()));
    writer.append(COMMA_DELIMITER);
    writer.append(transaction.getSource().toString());
    writer.append(NEW_LINE_SEPARATOR);
  }

  public void close() throws IOException {
    writer.flush();
    writer.close();
    
  }
}