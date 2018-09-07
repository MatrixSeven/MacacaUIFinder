package message;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya  ,\\
//		    // 狗神保佑 \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class Message {
  private EventType type;
  private Params message;

  private Message(EventType type, Params message) {
    this.type = type;
    this.message = message;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public Params getMessage() {
    return message;
  }

  public void setMessage(Params message) {
    this.message = message;
  }

  public static Message Create(EventType type, Params message){
    return new Message(type,message);
  }
  public static Message Create(Params message){
    return new Message(null,message);
  }

  @Override
  public String toString() {
    return "Message{" +
            "type=" + type +
            ", message=" + message +
            '}';
  }
}
