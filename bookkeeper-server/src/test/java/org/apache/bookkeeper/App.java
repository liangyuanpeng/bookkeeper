package org.apache.bookkeeper;

import org.apache.bookkeeper.client.AsyncCallback.DeleteCallback;
import org.apache.bookkeeper.client.BookKeeper;
import org.apache.bookkeeper.client.LedgerHandle;
import org.apache.bookkeeper.client.api.LedgerMetadata;
import org.apache.bookkeeper.client.api.LedgersIterator;
import org.apache.bookkeeper.client.api.ListLedgersResult;
import org.apache.bookkeeper.conf.ClientConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yunhorn lyp
 * @date 2021/5/11下午9:45
 */
public class App {
  public static void main(String[] args) {
    try {
      ClientConfiguration conf = new ClientConfiguration();
      conf.setBookieHealthCheckInterval(1,TimeUnit.SECONDS);
      conf.setMetadataServiceUri("etcd://192.168.31.90:2279/ledgers");
//      conf.setMetadataServiceUri("zk://127.0.0.1:2181/ledgers");
      String connectionString = "127.0.0.1:2181"; // For a single-node, local ZooKeeper cluster
      BookKeeper bkClient = BookKeeper.forConfig(conf).build();
//      BookKeeper bkClient = new BookKeeper(connectionString);
      TimeUnit.SECONDS.sleep(2);

      byte[] password = "some-password".getBytes();



      //创建ledger
      LedgerHandle handle = bkClient.createLedger(1,1,BookKeeper.DigestType.MAC, password);
      System.out.println(handle.getLedgerMetadata().getCtime());
      System.out.println(handle.getId());

      //往ledger写entry
      for (int i = 0; i < 2; i++) {
        long entryId = handle.addEntry((System.currentTimeMillis()+"").getBytes());
        System.out.println("entryId:"+entryId+",length:"+handle.getLedgerMetadata().getLength());

      }
      long entryId = handle.addEntry("Some entry data123".getBytes());

      System.out.println("entryId:"+entryId+",length:"+handle.getLedgerMetadata().getLength());


      //查询ledger list
      CompletableFuture<ListLedgersResult> completableFuture = bkClient.newListLedgersOp().execute();
      LedgersIterator iterator = completableFuture.get().iterator();
      while(iterator.hasNext()){
        long ledgeId = iterator.next();
        System.out.println("ledgeId:"+ledgeId);
        CompletableFuture<LedgerMetadata> ledgerMetadata = bkClient.getLedgerMetadata(ledgeId);
        //查询ledger最新的一条entry
        System.out.println("lastEntryId:"+ledgerMetadata.get().getLastEntryId());
      }
      //异步删除ledger
      bkClient.asyncDeleteLedger(2, new DeleteCallback() {
        @Override
        public void deleteComplete(int i, Object o) {
          System.out.println("deleteComplete:"+i+o);
        }
      },null);

      handle.close();
      bkClient.close();

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
