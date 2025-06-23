package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person p = Person.newBuilder()
                .setId(2)
                .setName(Person.Name.newBuilder().setValue("1234"))
                .setEmail(Person.Email.newBuilder().setValue("ttttttt").build())
                .addPhones(Person.PhoneNumber.newBuilder().setNumber("123434").build())
                .build();

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\81803\\Desktop\\dns\\Person.bin")) {
            p.writeTo(fos); // バイナリ形式で出力
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Person.parseFrom()
    }
}
