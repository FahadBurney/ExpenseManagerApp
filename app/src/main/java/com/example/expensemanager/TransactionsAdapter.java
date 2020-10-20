package com.example.expensemanager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>
{
private List<Transactions> transactions=new ArrayList<>();
private OnItemClickListener listener;
    // here we create and return transactions Holder
    @NonNull
    @Override
    // JO Views chahiye wo create karega aur viewholder mein  store karega
    public TransactionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transactions_items,parent,false);
        return new TransactionsHolder(itemView);
    }
// binds data with our views
    @Override
    public void onBindViewHolder(@NonNull TransactionsHolder holder, int position) {
        Transactions currentTransaction=transactions.get(position);
        holder.textViewAmount.setText(currentTransaction.getAmount()+"");
        holder.textViewCategory.setText(currentTransaction.getCategory());
        holder.textViewTransactionType.setText(currentTransaction.getTypeOfTransaction());
    }

    // how many items to display in recyclerView
    @Override
    public int getItemCount() {
        return transactions.size();
    }
    // Way to Get Notes In our RecyclerView
    public void setTransactions(List<Transactions> transactions)
    {
        this.transactions=transactions;
        notifyDataSetChanged();
    }
  class TransactionsHolder extends RecyclerView.ViewHolder
  {
      private TextView textViewAmount;
      private TextView textViewCategory;
      private TextView textViewTransactionType;

      public TransactionsHolder(@NonNull View itemView) {
          super(itemView);
          textViewAmount=(TextView) itemView.findViewById(R.id.text_view_amount);
          textViewCategory=(TextView) itemView.findViewById(R.id.text_view_category);
          textViewTransactionType=(TextView) itemView.findViewById(R.id.text_view_typeOfTransaction);
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  int position=getAdapterPosition();
                  if(listener!=null&&position!=RecyclerView.NO_POSITION)
                  {
                   listener.onItemClick(transactions.get(position),position);
                  }
              }
          });
      }
  }
  public interface OnItemClickListener
  {
      void onItemClick(Transactions transactions,int position);
  }
public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
}


}
