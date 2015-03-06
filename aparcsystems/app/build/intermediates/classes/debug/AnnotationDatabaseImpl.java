
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.google.inject.AnnotationDatabase;
import roboguice.fragment.FragmentUtil;

public class AnnotationDatabaseImpl extends AnnotationDatabase {

    public void fillAnnotationClassesAndFieldsNames(HashMap<String, Map<String, Set<String>>> mapAnnotationToMapClassWithInjectionNameToFieldSet) {
    
        String annotationClassName = null;
        Map<String, Set<String>> mapClassWithInjectionNameToFieldSet = null;
        Set<String> fieldNameSet = null;


        annotationClassName = "roboguice.inject.InjectView";
        mapClassWithInjectionNameToFieldSet = mapAnnotationToMapClassWithInjectionNameToFieldSet.get(annotationClassName);
        if( mapClassWithInjectionNameToFieldSet == null ) {
            mapClassWithInjectionNameToFieldSet = new HashMap<String, Set<String>>();
            mapAnnotationToMapClassWithInjectionNameToFieldSet.put(annotationClassName, mapClassWithInjectionNameToFieldSet);
        }

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("barcode");
        fieldNameSet.add("enterBarCode");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.ManualInputFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("bigImagePreview");
        fieldNameSet.add("deleteImage");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.BigImageFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("dateOfInfraction");
        fieldNameSet.add("photosAttachedContainer");
        fieldNameSet.add("vehicleMake");
        fieldNameSet.add("stillDispute");
        fieldNameSet.add("paidImage");
        fieldNameSet.add("vehicleLicenseNumber");
        fieldNameSet.add("violationDescription");
        fieldNameSet.add("comments3");
        fieldNameSet.add("comments2");
        fieldNameSet.add("comments1");
        fieldNameSet.add("vehicleStPr");
        fieldNameSet.add("amountTitle");
        fieldNameSet.add("photosAttachedText");
        fieldNameSet.add("amount");
        fieldNameSet.add("allegedInfraction");
        fieldNameSet.add("officer");
        fieldNameSet.add("vehicleLicenseExpiry");
        fieldNameSet.add("disputeButton");
        fieldNameSet.add("timeOfInfraction");
        fieldNameSet.add("photosSeparator");
        fieldNameSet.add("infractionLocation");
        fieldNameSet.add("choosePayment");
        fieldNameSet.add("vehicleMeter");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.ShowPenaltyFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("message");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.SuccessFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("lastName");
        fieldNameSet.add("relativeLayout");
        fieldNameSet.add("photosAttachedContainer");
        fieldNameSet.add("email");
        fieldNameSet.add("disputeButton");
        fieldNameSet.add("name");
        fieldNameSet.add("takePhotoButton");
        fieldNameSet.add("photosAttachedLogo");
        fieldNameSet.add("photosAttachedText");
        fieldNameSet.add("comments");
        fieldNameSet.add("mobile");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.DisputeFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("email");
        fieldNameSet.add("cvc");
        fieldNameSet.add("creditcard");
        fieldNameSet.add("date");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.PaymentFragment", fieldNameSet);


        annotationClassName = "com.google.inject.Inject";
        mapClassWithInjectionNameToFieldSet = mapAnnotationToMapClassWithInjectionNameToFieldSet.get(annotationClassName);
        if( mapClassWithInjectionNameToFieldSet == null ) {
            mapClassWithInjectionNameToFieldSet = new HashMap<String, Set<String>>();
            mapAnnotationToMapClassWithInjectionNameToFieldSet.put(annotationClassName, mapClassWithInjectionNameToFieldSet);
        }

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("bus");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.task.RestAsyncTask", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("bus");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.ui.fragment.BaseFragment", fieldNameSet);

        fieldNameSet = new HashSet<String>();
        fieldNameSet.add("client");
        fieldNameSet.add("context");
        mapClassWithInjectionNameToFieldSet.put("com.aparcsystems.task.ShowPenaltyTask", fieldNameSet);

    }
    
    public void fillAnnotationClassesAndMethods(HashMap<String, Map<String, Set<String>>> mapAnnotationToMapClassWithInjectionNameToMethodsSet) {
    }
    
    public void fillAnnotationClassesAndConstructors(HashMap<String, Map<String, Set<String>>> mapAnnotationToMapClassWithInjectionNameToConstructorsSet) {

        String annotationClassName = null;
        Map<String, Set<String>> mapClassWithInjectionNameToConstructorSet = null;
        Set<String> constructorSet = null;


        annotationClassName = "com.google.inject.Inject";
        mapClassWithInjectionNameToConstructorSet = mapAnnotationToMapClassWithInjectionNameToConstructorsSet.get(annotationClassName);
        if( mapClassWithInjectionNameToConstructorSet == null ) {
            mapClassWithInjectionNameToConstructorSet = new HashMap<String, Set<String>>();
            mapAnnotationToMapClassWithInjectionNameToConstructorsSet.put(annotationClassName, mapClassWithInjectionNameToConstructorSet);
        }

        constructorSet = new HashSet<String>();
        constructorSet.add("<init>:android.app.Application");
        mapClassWithInjectionNameToConstructorSet.put("com.aparcsystems.module.ApplicationModuleProvider", constructorSet);

        constructorSet = new HashSet<String>();
        constructorSet.add("<init>:android.content.Context");
        mapClassWithInjectionNameToConstructorSet.put("com.aparcsystems.task.RestAsyncTask", constructorSet);

    }
    
    public void fillClassesContainingInjectionPointSet(HashSet<String> classesContainingInjectionPointsSet) {
        classesContainingInjectionPointsSet.add("com.aparcsystems.module.ApplicationModuleProvider");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.BigImageFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.ManualInputFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.ShowPenaltyFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.task.RestAsyncTask");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.SuccessFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.DisputeFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.BaseFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.ui.fragment.PaymentFragment");
        classesContainingInjectionPointsSet.add("com.aparcsystems.task.ShowPenaltyTask");
    }
    

    public void fillBindableClasses(HashSet<String> injectedClasses) {
        injectedClasses.add("android.widget.Button");
        injectedClasses.add("com.aparcsystems.client.AndroidClient");
        injectedClasses.add("com.aparcsystems.ui.view.RoundedImageView");
        injectedClasses.add("android.content.Context");
        injectedClasses.add("android.view.ViewGroup");
        injectedClasses.add("android.view.View");
        injectedClasses.add("android.widget.EditText");
        injectedClasses.add("android.widget.TextView");
        injectedClasses.add("android.widget.ImageView");
        injectedClasses.add("com.aparcsystems.otto.Bus");
        injectedClasses.add("android.app.Application");
        injectedClasses.add("android.widget.RelativeLayout");

        if(FragmentUtil.hasNative) {
            injectedClasses.add("android.app.FragmentManager");
        }

        if(FragmentUtil.hasSupport) {
            injectedClasses.add("android.support.v4.app.FragmentManager");
        }
    }

}
