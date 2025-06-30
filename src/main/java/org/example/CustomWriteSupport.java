package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

import java.util.HashMap;

public class CustomWriteSupport extends WriteSupport<Persons> {
    private RecordConsumer c;
    private MessageType s;

    public CustomWriteSupport(MessageType s) {
        this.s = s;
    }

    @Override
    public WriteContext init(Configuration configuration) {
        return new WriteContext(s, new HashMap<>());
    }

    @Override
    public void prepareForWrite(RecordConsumer recordConsumer) {
        c = recordConsumer;
    }

    @Override
    public void write(Persons record) {
        var ids = record.getIds();
        var names = record.getNames();

        c.startMessage();

        c.startField("persons", 0);
        c.startGroup();

        c.startField("list", 0);
        c.startGroup();


        for(int i = 0; i < ids.length; i++){
            c.startField("element", 0);
            c.startGroup();

            c.startField("id", 0);
            c.addInteger(ids[i]);
            c.endField("id", 0);

            c.startField("name", 1);
            c.addBinary(Binary.fromString(names[i]));
            c.endField("name", 1);

            c.endGroup();
            c.endField("element", 0);
        }

        c.endGroup();
        c.endField("list", 0);

        c.endGroup();
        c.endField("persons", 0);

        c.endMessage();
    }
}
