package com.github.hanalee.searchimagewithpaging3.domain.repository

import android.app.Application
import com.github.hanalee.searchimagewithpaging3.di.module.repositoryModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertNotNull


class SearchImageRepositoryTest : KoinTest {
    private val searchImageRepository: SearchImageRepository by inject()

    @Mock
    private lateinit var context: Application


    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        startKoin {
            modules(repositoryModule)
            androidContext(context)
        }
    }


    @Test
    fun testSearchImage() {
        checkModules {
            modules(
                repositoryModule
            )
        }

        runBlocking {
            //given
            val keyword = "바지"
            val page = 1
            //when
            val result = searchImageRepository.searchImage(keyword, page = page)
            //then
            assertNotNull(result)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

}