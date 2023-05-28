package models

import (
	"ZenZen_API/app"
	_ "ZenZen_API/app"
	_ "ZenZen_API/framework/utils"
	_ "errors"
	_ "github.com/gofiber/fiber/v2"
	"gorm.io/gorm"
)

type Note struct {
	gorm.Model
	Title       string `gorm:""`
	Description string `gorm:""`
	Due         string `gorm:""`
}

func (n Note) CreateNote() (Note, error) {
	if err := app.DB.Create(&n).Error; err != nil {
		return Note{}, err
	}
	return n, nil
}

//func (n Note) GetNoteByID(uint id) interface{} {
//
//	return nil
//}
