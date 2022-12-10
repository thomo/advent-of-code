package aoc.util.ddd

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Coord3dTest {
	@Test
	fun shouldRotateX() {
		assertAll(
			{ assertEquals(Coord3d(2, 3, 4), Coord3d(2, 3, 4).rotateX(Degree90.D0), "D0") },
			{ assertEquals(Coord3d(2, -4, 3), Coord3d(2, 3, 4).rotateX(Degree90.D90), "D90") },
			{ assertEquals(Coord3d(2, -3, -4), Coord3d(2, 3, 4).rotateX(Degree90.D180), "D180") },
			{ assertEquals(Coord3d(2, 4, -3), Coord3d(2, 3, 4).rotateX(Degree90.D270), "D270") }
		)
	}

	@Test
	fun shouldRotateY() {
		assertAll(
			{ assertEquals(Coord3d(2, 3, 4), Coord3d(2, 3, 4).rotateY(Degree90.D0), "D0") },
			{ assertEquals(Coord3d(4, 3, -2), Coord3d(2, 3, 4).rotateY(Degree90.D90), "D90") },
			{ assertEquals(Coord3d(-2, 3, -4), Coord3d(2, 3, 4).rotateY(Degree90.D180), "D180") },
			{ assertEquals(Coord3d(-4, 3, 2), Coord3d(2, 3, 4).rotateY(Degree90.D270), "D270") }
		)
	}

	@Test
	fun shouldRotateZ() {
		assertAll(
			{ assertEquals(Coord3d(2, 3, 4), Coord3d(2, 3, 4).rotateZ(Degree90.D0), "D0") },
			{ assertEquals(Coord3d(-3, 2, 4), Coord3d(2, 3, 4).rotateZ(Degree90.D90), "D90") },
			{ assertEquals(Coord3d(-2, -3, 4), Coord3d(2, 3, 4).rotateZ(Degree90.D180), "D180") },
			{ assertEquals(Coord3d(3, -2, 4), Coord3d(2, 3, 4).rotateZ(Degree90.D270), "D270") }
		)
	}

	@Test
	internal fun shouldGenerateAllOrientation() {
		val result = Coord3d(2, 4, 3).orientations()

		org.junit.jupiter.api.assertAll(
			{ assertEquals(24, result.size) },

			{ Assertions.assertTrue(result.contains(Coord3d(2, 3, -4)), "( 2, 3,-4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(2, 4, 3)), "( 2, 4, 3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(2, -3, 4)), "( 2,-3, 4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(2, -4, -3)), "( 2,-4,-3)") },

			{ Assertions.assertTrue(result.contains(Coord3d(3, 2, 4)), "( 3, 2, 4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(3, 4, -2)), "( 3, 4,-2)") },
			{ Assertions.assertTrue(result.contains(Coord3d(3, -2, -4)), "( 3,-2,-4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(3, -4, 2)), "( 3,-4, 2)") },

			{ Assertions.assertTrue(result.contains(Coord3d(4, 2, -3)), "( 4, 2,-3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(4, 3, 2)), "( 4, 3, 2)") },
			{ Assertions.assertTrue(result.contains(Coord3d(4, -2, 3)), "( 4,-2, 3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(4, -3, -2)), "( 4,-3,-2)") },

			{ Assertions.assertTrue(result.contains(Coord3d(-2, 3, 4)), "(-2, 3, 4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-2, 4, -3)), "(-2, 4,-3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-2, -3, -4)), "(-2,-3,-4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-2, -4, 3)), "(-2,-4, 3)") },

			{ Assertions.assertTrue(result.contains(Coord3d(-3, 2, -4)), "(-3, 2,-4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-3, 4, 2)), "(-3, 4, 2)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-3, -2, 4)), "(-3,-2, 4)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-3, -4, -2)), "(-3,-4,-2)") },

			{ Assertions.assertTrue(result.contains(Coord3d(-4, 2, 3)), "(-4, 2, 3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-4, 3, -2)), "(-4, 3,-2)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-4, -2, -3)), "(-4,-2,-3)") },
			{ Assertions.assertTrue(result.contains(Coord3d(-4, -3, 2)), "(-4,-3, 2)") }
		)
	}
}
