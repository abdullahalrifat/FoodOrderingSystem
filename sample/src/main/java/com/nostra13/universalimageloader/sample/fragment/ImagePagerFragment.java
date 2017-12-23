/*******************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.nostra13.universalimageloader.sample.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.sample.Constants;
import com.nostra13.universalimageloader.sample.R;
import com.nostra13.universalimageloader.sample.activity.ChoiceNumber;
import com.nostra13.universalimageloader.sample.activity.MainActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerFragment extends BaseFragment {

	public String ip="192.168.0.6";
	public static final int INDEX = 2;
	private static  String[] IMAGE_URLS;
	private static  String[] Names;
	private static  String[] Prices;
	private  EditText ItemNote;
	private  EditText ItemQuantity;

	private TextView ItemName;
	private TextView ItemPrice;

	private  Button choose;
	public ViewPager pager;
	private  String messsage;
	private Socket client;
	private PrintWriter printwriter;

	View imageLayout,opt;
	String quantity;
	String note;
	String name;
	String price;
	ChoiceNumber choice= MainActivity.getChoiceClass();
	public ImagePagerFragment()
	{
		choice.getImages();
		IMAGE_URLS=choice.getIMAGES();
		Names=choice.getNames();
		Prices=choice.getPrices();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fr_image_pager, container, false);
		 pager= (ViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(new ImageAdapter(getActivity()));
		pager.setCurrentItem(getArguments().getInt(Constants.Extra.IMAGE_POSITION, 0));
		return rootView;
	}
	//updating the whole pager and get back to the main menu
	public void updatePager()
	{
		pager.getAdapter().notifyDataSetChanged();
	}
	private  class ImageAdapter extends PagerAdapter {

		private LayoutInflater inflater;
		private DisplayImageOptions options;

		ImageAdapter(Context context) {
			inflater = LayoutInflater.from(context);

			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.ic_empty)
					.showImageOnFail(R.drawable.ic_error)
					.resetViewBeforeLoading(true)
					.cacheOnDisk(true)
					.imageScaleType(ImageScaleType.EXACTLY)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.considerExifParams(true)
					.displayer(new FadeInBitmapDisplayer(300))
					.build();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		//for setting the names and prices
		@Override
		public void setPrimaryItem(ViewGroup container, int position, Object object)
		{
			super.setPrimaryItem(container, position, object);

		}

		@Override
		public int getCount() {
			return IMAGE_URLS.length;
		}
		@Override
		public Object instantiateItem(ViewGroup view, final int position)
		{
			imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
			assert imageLayout != null;

			//image is not loading every time
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);


			opt = inflater.inflate(R.layout.item_pager_image, view, true);


			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
			//,Names[position],Prices[position],imageLayout,
			ImageLoader.getInstance().loadImage(IMAGE_URLS[position],options, new SimpleImageLoadingListener()
			{
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					ItemName=(TextView) opt.findViewById(R.id.ItemName);
					ItemPrice=(TextView)opt.findViewById(R.id.ItemPrice);
					ItemName.setText("Name\t:"+Names[position]);
					ItemPrice.setText(Prices[position]);


					ItemNote=(EditText) opt.findViewById(R.id.ItemNote);
					ItemQuantity=(EditText)opt.findViewById(R.id.ItemQuantity);



					choose=(Button) opt.findViewById(R.id.choose);
					choose.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v)
						{
							quantity=ItemQuantity.getText().toString();
							note=ItemNote.getText().toString();
							name=ItemName.getText().toString();
							price=ItemPrice.getText().toString();
							try
							{
								Double totalBill=(Double.parseDouble(price))*(Double.parseDouble(quantity));
								choice.inTotalBill+=totalBill;
								price=Double.toString(totalBill);
								//ItemNote.setText(""); // Reset the text field to blank
							}catch(Exception e)
							{

							}
							//getting input for item quantity and Item Extraa note
							String Order= " "+name+" "+'('+quantity+')'+" With "+note+" "+"Price\t:"+price+" Tk"+'\n';
							ItemQuantity.setText(""); // Reset the text field to blank
							ItemNote.setText("");
							messsage = Order; // get the text message on the text field
							SendMessage sendMessageTask = new SendMessage();
							sendMessageTask.execute();
						}
					});
				}
			});
			ImageLoader.getInstance().displayImage(IMAGE_URLS[position], imageView, options, new SimpleImageLoadingListener() {

				@Override
				public void onLoadingStarted(String imageUri, View view)
				{
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
				{
					spinner.setVisibility(View.GONE);
				}
			});
			view.addView(imageLayout, 0);
			return imageLayout;
		}

		public class SendMessage extends AsyncTask<Void, Void, Void> {

			@Override
			protected Void doInBackground(Void... params)
			{
				try {

					//10.0.2.2 while using in emulator
					//192.168.1.105 my pcs ip while using on real device  get by ifconfig on linux
					//192.168.21.68 in study room 1 wifi my pc's ip
					client = new Socket(ip, 6789); // connect to the server
					printwriter = new PrintWriter(client.getOutputStream(), true);
					printwriter.write(messsage); // write the message to output stream

					printwriter.flush();
					printwriter.close();
					client.close(); // closing the connection

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}
}