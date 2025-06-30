package org.example;

import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

import static org.apache.parquet.schema.OriginalType.LIST;
import static org.apache.parquet.schema.OriginalType.UTF8;

public class SchemaGenerator {
    public MessageType generate(){
        return Types.buildMessage()
                .requiredGroup().as(LIST)
                .repeatedGroup()
                .requiredGroup()
                .required(PrimitiveType.PrimitiveTypeName.INT32).named("id")
                .required(PrimitiveType.PrimitiveTypeName.BINARY).as(UTF8).named("name")
                .named("element")
                .named("list")
                .named("persons")
                .named("schema");
    }
}
