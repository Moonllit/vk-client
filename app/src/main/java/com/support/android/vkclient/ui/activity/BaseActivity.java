package com.support.android.vkclient.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;

import androidx.core.content.FileProvider;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private String currentPhotoPath;

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + DateTime.now().toString("yyyyMMdd_HHmmss") + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.getStackTrace();
                Toast.makeText(this, "Ошибка при создании файла",
                        Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    onPhotoCreated(Uri.parse(currentPhotoPath));
                    break;
            }
        }
    }

    protected void onPhotoCreated(Uri uri) {
        //
    }

    protected final String getPhotoImagePath() {
        return currentPhotoPath;
    }
}
