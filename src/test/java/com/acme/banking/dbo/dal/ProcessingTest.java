package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldGetAccountWhenClientAndAccountsExists() {
        //region given
        //xUnit Patterns: dest-doublers
        //dummy
        //stub
        final UUID clientUuidStub = UUID.randomUUID();

        final SavingAccount accountStub1 = mock(SavingAccount.class);
        final SavingAccount accountStub2 = mock(SavingAccount.class);
        when(accountStub1.getAmount()).thenReturn(1.);
        when(accountStub2.getAmount()).thenReturn(2.);

        final AccountRepository accountRepositoryStub = mock(AccountRepository.class);
        when(accountRepositoryStub.findAccountsByClientId(any(UUID.class)))
                .thenReturn(Collections.emptyList())
                .thenReturn(Arrays.asList(
                    accountStub1, accountStub2
                ));
        final Processing sut = new Processing(accountRepositoryStub);

        //region when
        final Collection<Account> accountsByClientId = sut.getAccountsByClientId(clientUuidStub);
        //endregion

        //region then
        assertThat(accountsByClientId).hasSize(2);
        accountsByClientId.forEach( a -> System.out.println(a.getId()));
        //endregion
    }

    @Test
    public void shouldDepositAndWithdrawWhenTransfer() {
        final SavingAccount accountMock1 = mock(SavingAccount.class);
        final SavingAccount accountMock2 = mock(SavingAccount.class);

        final AccountRepository accountRepositoryStub = mock(AccountRepository.class);
        when(accountRepositoryStub.findAccountById(any(UUID.class)))
                .thenReturn(accountMock1)
                .thenReturn(accountMock2);
        final Processing sut = new Processing(accountRepositoryStub);

        sut.transfer(1., UUID.randomUUID(), UUID.randomUUID());

        verify(accountMock1, times(1)).withdraw(1.);
        verify(accountMock2).deposit(anyDouble());
    }
}
