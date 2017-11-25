package com.sm_arts.jibcon.ui.main.devicemenu.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.swipe.SwipeLayout;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.ui.main.devicemenu.adapter.viewholder.DeviceMenuViewHolder;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends RecyclerView.Adapter<DeviceMenuViewHolder> {
    private static final String TAG = "DeviceMenuAdapter";
    private List<DeviceItem> mDeviceItems;

    private CustomItemClickListener mDeviceItemIvClickedListener;
    private CustomItemClickListener mSubscribeIvClickedListener;
    private CustomItemClickListener mThreedotIvClickedListener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;



    public DeviceMenuAdapter(List<DeviceItem> deviceItems,
                             CustomItemClickListener deviceItemIvClickedListener,
                             CustomItemClickListener subscribeIvClickedListener,
                             CustomItemClickListener threedotIvClicked) {
//        Log.d(TAG, "DeviceMenuAdapter: ");
        this.mDeviceItems = deviceItems;
        this.mDeviceItemIvClickedListener = deviceItemIvClickedListener;
        this.mSubscribeIvClickedListener = subscribeIvClickedListener;
        this.mThreedotIvClickedListener = threedotIvClicked;
    }

    public void setDeviceItems(List<DeviceItem> mDeviceItems) {
//        Log.d(TAG, "setDeviceItems: ");
        this.mDeviceItems = mDeviceItems;
        notifyDataSetChanged();
    }

    @Override
    public DeviceMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder: ");
        View deviceMenuView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.maindevicemenu_devicemenu_item,
                        parent, false);

        SwipeLayout item = (SwipeLayout) deviceMenuView.findViewById(R.id.swipe_item);
        item.setShowMode(SwipeLayout.ShowMode.PullOut);
        item.addDrag(SwipeLayout.DragEdge.Left, item.findViewById(R.id.bottom_wrapper));
        item.addDrag(SwipeLayout.DragEdge.Right, item.findViewById(R.id.bottom_wrapper_2));
        final DeviceMenuViewHolder deviceMenuViewHolder =
                new DeviceMenuViewHolder(deviceMenuView,
                        mDeviceItemIvClickedListener,
                        mSubscribeIvClickedListener,
                        mThreedotIvClickedListener);

        return deviceMenuViewHolder;
    }




    @Override
    public void onBindViewHolder(DeviceMenuViewHolder holder, int position) {
        DeviceItem deviceItem = mDeviceItems.get(position);
        holder.configureWith(deviceItem);
    }

    @Override
    public int getItemCount() {
        return mDeviceItems.size();
    }

    public DeviceItem getItemWithPosition(int position) {
        if(getItemCount() > position) {
            return mDeviceItems.get(position);
        } else {
            return null;
        }
    }

    public int findDeviceItemPositionWithSur(String sur) {
        Log.d(TAG, "findDeviceItemPositionWithSur() called with: topic = [" + sur + "]");
        for (int idx = 0; idx < mDeviceItems.size(); idx++) {
            String itemSur = mDeviceItems.get(idx).getSubscriptionSur();
            if (itemSur != null) {
                if (TextUtils.equals(itemSur, sur)) {
                    Log.d(TAG, "findDeviceItemPositionWithSur: found idx=" + idx);
                    return idx;
                }
            }
        }

        return -1;
    }

    // TODO: 8/9/17 같은 기기를 등록해서 여러개의 센서값이 같이변함 실제론 같은기기 등록될 일 없으므로 지워야함
    public List<Integer> findDeviceItemPositionsWithSur(String sur) {
        List<Integer> results = new ArrayList<>();
        Log.d(TAG, "findDeviceItemPositionWithSur() called with: topic = [" + sur + "]");
        for (int idx = 0; idx < mDeviceItems.size(); idx++) {
            DeviceItem item = mDeviceItems.get(idx);
            if (item.isSubscribeOnOffState()) {
                String itemSur = item.getSubscriptionSur();
                if (TextUtils.equals(itemSur, sur)) {
                    Log.d(TAG, "findDeviceItemPositionWithSur: found idx=" + idx);
                    results.add(idx);
                }
            }
        }

        return results;
    }

    public void showContent(int position, String con) {
        Log.d(TAG, "showContent() called with: position = [" + position + "], con = [" + con + "]");
        mDeviceItems.get(position).setContent(con);
        notifyItemChanged(position);
    }
}