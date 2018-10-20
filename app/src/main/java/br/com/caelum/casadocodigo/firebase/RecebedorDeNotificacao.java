package br.com.caelum.casadocodigo.firebase;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.activity.CarrinhoActivity;

public class RecebedorDeNotificacao extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String message = recuperaDados(remoteMessage);

        PendingIntent procuracao = criaProcuracao();

        Notification notification = criaNotificao(message, procuracao);

        mostra(notification);
    }

    private String recuperaDados(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        return data.get("message");
    }

    private PendingIntent criaProcuracao() {
        return PendingIntent
                    .getActivity(this,
                            123,
                            new Intent(this, CarrinhoActivity.class),
                            PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Notification criaNotificao(String message, PendingIntent procuracao) {
        return new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.casadocodigo)
                    .setContentTitle("Nova notificação")
                    .setContentText(message)
                    .setContentIntent(procuracao)
                    .setAutoCancel(true)
                    .build();
    }

    private void mostra(Notification notification) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(123, notification);
    }

}
