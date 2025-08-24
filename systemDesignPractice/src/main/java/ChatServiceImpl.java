import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public void sendMessage(ChatRequest request, StreamObserver<ChatResponse> responseObserver) {
        System.out.println("Received message from user " + request.getUserId() + ": " + request.getMessage());

        // Simulate storing message in a database (optional)
        ChatResponse response = ChatResponse.newBuilder().setStatus("Message Received").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

