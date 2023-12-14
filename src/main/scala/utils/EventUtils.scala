package utils

import java.sql.Timestamp
import java.util.UUID

object EventUtils {
  def insertEvent(eventType:String,entityId:String, entityType:String,  eventDataJson:String = null): Boolean = {
    val eventUUID: String = UUID.randomUUID().toString
    val timestamp: Long = System.currentTimeMillis()
    // Convert to java.sql.Timestamp
    val sqlTimestamp: Timestamp = new Timestamp(timestamp)

    val insertSql: String =
      s"INSERT INTO Events (event_id, event_type, entity_id, entity_type, event_data, time_stamp) VALUES ('$eventUUID', '$eventType', '$entityId', '$entityType', '$eventDataJson', '$sqlTimestamp')"
    val isSuccess = DatabaseUtils.executeCreateUpdateDeleteQuery(insertSql);
    return isSuccess;
  }
}
