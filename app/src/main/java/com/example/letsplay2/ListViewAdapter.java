package com.example.letsplay2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter
{
    Activity context;
    ArrayList<User> users;
    private ArrayList<User> arraylist ;

    public ListViewAdapter(Activity context, ArrayList<User> users) {
        super();
        this.context = context;
        this.users = users;
        arraylist = new ArrayList<>();
        arraylist.addAll(users);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return users.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtViewName;
        TextView txtViewAddress;
        TextView txtViewSport;
        TextView txtViewEmail;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.txtViewName = (TextView) convertView.findViewById(R.id.user_name);
            holder.txtViewAddress = (TextView) convertView.findViewById(R.id.user_address);
            holder.txtViewSport = (TextView) convertView.findViewById(R.id.user_sport);
            holder.txtViewEmail = (TextView) convertView.findViewById(R.id.user_email);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtViewName.setText(users.get(position).name);
        holder.txtViewAddress.setText(users.get(position).address);
        holder.txtViewSport.setText(users.get(position).sports);
        holder.txtViewEmail.setText(users.get(position).mail);


        return convertView;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        users.clear();
        if (charText.length() == 0) {
            users.addAll(arraylist);
        }
        else
        {
            for (User wp : arraylist)
            {
                boolean flag = false;
                if (wp.address.toLowerCase(Locale.getDefault()).contains(charText))
                {
                    flag = true;
                    users.add(wp);
                }
                if (wp.sports.toLowerCase(Locale.getDefault()).contains(charText) && !flag)
                {
                    users.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
