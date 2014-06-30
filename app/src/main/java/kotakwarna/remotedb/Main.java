package kotakwarna.remotedb;

import android.app.Fragment;

import kotakwarna.remotedb.beans.MaterialBean;

/**
 * Created by Fajar on 6/26/2014.
 */
public interface Main {

    void onChangeFragment(String fragmentTag);
    void onChangeFragmentToMaterialDetailItem(MaterialBean materialBean);
    void onChangeFragmentToMaterialDetailEdit(MaterialBean materialBean);
    void onChangeFragmentBackToFragmentBeforeEdit();

}
