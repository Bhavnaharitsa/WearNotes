import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wearapp.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position==0){
            convertView= LayoutInflater.from(this.getContext()).inflate(R.layout.new_note,parent,false);
        }
        convertView= LayoutInflater.from(this.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        ContactsContract.CommonDataKinds.Note note= (ContactsContract.CommonDataKinds.Note) getItem(position);
        TextView title=(TextView)convertView.findViewById(android.R.id.text1);
        title.setText(note.toString());
        return convertView;
    }

    public ListViewAdapter(Context context, int resource, List<ContactsContract.CommonDataKinds.Note> objects) {
        super(context, resource,objects);
    }
}
