package com.kin.ecosystem;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import com.kin.ecosystem.base.Observer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kin.core.KinAccount;

public interface AccountManager {

	int REQUIRE_CREATION = 0x00000001;
	int PENDING_CREATION = 0x00000002;
	int REQUIRE_TRUSTLINE = 0x00000003;
	int CREATION_COMPLETED = 0x00000004;
	int ERROR = 0x00000005;

	@IntDef({REQUIRE_CREATION,
		PENDING_CREATION,
		REQUIRE_TRUSTLINE,
		CREATION_COMPLETED,
		ERROR})
	@Retention(RetentionPolicy.SOURCE)
	@interface AccountState {

	}

	void start(@NonNull final KinAccount kinAccount);

	void retry();

	@AccountState
	int getAccountState();

	boolean isAccountCreated();

	void addAccountStateObserver(@NonNull final Observer<Integer> observer);

	void removeAccountStateObserver(@NonNull final Observer<Integer> observer);

	interface Local {

		int getAccountState();

		void setAccountState(@AccountState int accountState);
	}
}
