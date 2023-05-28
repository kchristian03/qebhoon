package NoteController

import (
	"ZenZen_API/app"
	"ZenZen_API/app/models"
	"ZenZen_API/framework/utils"
	"github.com/gofiber/fiber/v2"
)

// DeleteNoteHandler
// This is an example of a handler
// Modify it to suit your needs
func DeleteNoteHandler(c *fiber.Ctx) error {

	var note models.Note
	app.DB.First(&note, c.Params("id"))
	app.DB.Delete(&note)

	return c.Status(fiber.StatusOK).JSON(utils.HttpResponse{
		Success: true,
		Message: "Note " + note.Title + " deleted successfully",
		Data:    note,
	})
}
