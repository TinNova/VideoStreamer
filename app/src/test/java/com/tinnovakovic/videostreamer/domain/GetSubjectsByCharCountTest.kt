package com.tinnovakovic.videostreamer.domain

import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.models.Subject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetSubjectsByCharCountTest {

    private val streamerRepo: StreamerRepo = mockk(relaxed = true)
    private val sut = GetSubjectsByCharCount(
        streamerRepo = streamerRepo
    )

    @Test
    fun `GIVEN list of subjects with 5 chars each for title AND CharCountLimit of 2 WHEN execute() THEN return 5 subjects`() {
        //GIVEN
        val subject = Subject(id = 0, title = "Title")
        val expected = listOf(subject, subject, subject)

        every { streamerRepo.getSubjects() } returns expected

        //WHEN
        val result = sut.execute()

        //THEN
        assertEquals(
            expected, result
        )
    }

    @Test
    fun `GIVEN list of subjects with 1 and 5 chars count for title AND CharCountLimit of 2 WHEN execute() THEN return subjects with 5 chars only`() {
        //GIVEN
        val shortSubject = Subject(id = 0, title = "T")
        val subject = Subject(id = 0, title = "Title")
        val subjects = listOf(shortSubject, shortSubject, subject, subject, subject)
        val expected = listOf(subject, subject, subject)

        every { streamerRepo.getSubjects() } returns subjects

        //WHEN
        val result = sut.execute()

        //THEN
        assertEquals(
            expected, result
        )
    }

    @Test
    fun `GIVEN emptyList WHEN execute() THEN return emptyList`() {
        //GIVEN
        val expected = emptyList<Subject>()
        every { streamerRepo.getSubjects() } returns expected

        //WHEN
        val result = sut.execute()

        //THEN
        assertEquals(
            expected, result
        )
    }

}
