package ru.mitin.vladislav.litebox.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.mitin.vladislav.litebox.R;
import ru.mitin.vladislav.litebox.presenters.GoogleSearchPresenter;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ResultViewHolder> {
    private Context context;
    private List<GoogleSearchPresenter.SearchResultViewDto> result;

    public ResultRecyclerViewAdapter(Context context, List<GoogleSearchPresenter.SearchResultViewDto> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bind(result.get(position));
    }

    @Override
    public int getItemCount() {
        return result != null ? result.size() : 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView url;
        private TextView description;

        public ResultViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            url = itemView.findViewById(R.id.url);
            description = itemView.findViewById(R.id.description);
        }

        private void bind(GoogleSearchPresenter.SearchResultViewDto dto) {
            title.setText(dto.title);

            String text = "<a href=" + dto.url + ">" + dto.url + "</a>";
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setText(Html.fromHtml(text));

            description.setText(dto.description);
        }
    }
}
