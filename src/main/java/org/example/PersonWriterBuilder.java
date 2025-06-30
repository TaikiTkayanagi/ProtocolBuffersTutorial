package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.schema.MessageType;

public class PersonWriterBuilder extends ParquetWriter.Builder<Persons, PersonWriterBuilder> {
    private MessageType s;
    protected PersonWriterBuilder(OutputFile path, MessageType s) {
        super(path);
        this.s = s;
    }

    @Override
    protected PersonWriterBuilder self() {
        return this;
    }

    @Override
    public PersonWriterBuilder withWriteMode(ParquetFileWriter.Mode mode) {
        return super.withWriteMode(mode);
    }

    @Override
    protected WriteSupport<Persons> getWriteSupport(Configuration conf) {
        return new CustomWriteSupport(s);
    }
}
