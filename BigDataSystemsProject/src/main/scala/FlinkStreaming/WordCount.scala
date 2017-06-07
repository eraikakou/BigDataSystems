package FlinkStreaming

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * Created by aggelikiromanou on 07/06/2017.
  */

object WordCount {

  def main(args: Array[String]) {

    type WordCount = (String, Int)

    def countWords(lines: DataStream[String], stopWords: Set[String], window: Time): DataStream[WordCount] = {
      lines
        .flatMap(line => line.split(" "))
        .filter(word => !word.isEmpty)
        .map(word => word.toLowerCase)
        .filter(word => !stopWords.contains(word))
        .map(word => (word, 1))
        .keyBy(0)
        .timeWindow(window)
        .sum(1)
    }
  }
}
