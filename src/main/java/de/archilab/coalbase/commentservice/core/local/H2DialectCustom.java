package de.archilab.coalbase.commentservice.core.local;

import org.hibernate.dialect.H2Dialect;

import java.sql.Types;

/*only needed for local dev, enables support for postgreSQL TYPES and H2 TYPES*/
public class H2DialectCustom extends H2Dialect {

  public H2DialectCustom() {
    super();
    this.registerColumnType(Types.BINARY, "uuid");
    this.registerColumnType(Types.BINARY, "varbinary");
  }
}
