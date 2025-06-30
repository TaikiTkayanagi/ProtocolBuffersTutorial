package org.example;

import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.io.LocalOutputFile;
import org.apache.parquet.proto.ProtoParquetWriter;
import org.apache.parquet.proto.ProtoWriteSupport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Person p = Person.newBuilder()
                .setId(500000000)
                .setName(Person.Name.newBuilder().setValue("1234").setValue2("5678"))
                .setTest1(10.0000000000)
                .setTest2(1000000000)
                .setTest3(10)
                .build();

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\81803\\Desktop\\dns\\Person.bin")) {
            p.writeTo(fos); // バイナリ形式で出力
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path path = Path.of("C:\\Users\\81803\\Desktop\\dns\\Person.parquet");
        var outputPath = new LocalOutputFile(path);

        try(ParquetWriter<Person> writer = ProtoParquetWriter.<Person>builder(outputPath)
                .withMessage(Person.class)
                .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
                .config(ProtoWriteSupport.PB_SPECS_COMPLIANT_WRITE, "true")
                .build()){
            writer.write(p);
        }

        int[] ids = new int[]{2,1,2,3};
        String[] names = new String[]{"0","1","2","3"};
        var persons = new Persons(ids, names);
        try(var w = new PersonWriterBuilder(outputPath, new SchemaGenerator().generate()).withWriteMode(ParquetFileWriter.Mode.OVERWRITE).build()){
            w.write(persons);
        }
    }
}
