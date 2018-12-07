package in.tvac.akshayejh.photoblog;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    BlogRecyclerAdapter adapter;
    ArrayList<BlogPost> filterList;

    public CustomFilter(ArrayList<BlogPost> filterList,BlogRecyclerAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<BlogPost> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getUser_id().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.blog_list= (ArrayList<BlogPost>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
