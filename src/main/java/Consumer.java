import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    public static void main(String[] args) throws Exception {
        System.out.println("Consumer");
        String NOME_FILA = "filaOla";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection conexao = connectionFactory.newConnection();

        //criando um canal e declarando uma fila
        Channel canal = conexao.createChannel();
        canal.queueDeclare(NOME_FILA, false, false, false, null);

        //Definindo a funcao callback
        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Consumer: " + mensagem);
        };

        //Consome da fila
        canal.basicConsume(NOME_FILA, true, callback, consumerTag -> {});
        System.out.println("Continuarei executando outras atividades..");
    }
}
