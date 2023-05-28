package models

import (
	"ZenZen_API/app"
	"errors"
	"github.com/gofiber/fiber/v2"
	"gorm.io/gorm"
	"time"
)

type User struct {
	gorm.Model
	PersonalAccessToken []PersonalAccessToken `gorm:"foreignKey:UserID"`
	//Notes               []Note                `gorm:"foreignKey:UserID"`
	Name       string    `gorm:"not null"`
	Email      string    `gorm:"unique"`
	Username   string    `gorm:"unique"`
	Password   string    `json:""`
	Streak     int       `json:""`
	StreakDate time.Time `json:""`
	ProfilePic string    `json:""`
}

func (_ User) Login(username string, password string) (*User, error) {
	var user User

	app.DB.Where("username = ?", username).First(&user)

	if app.Utilities.Crypto.VerifyPassword(password, user.Password) {
		return &user, nil
	}

	return nil, errors.New("Invalid username or password")
}

func (u User) Register() (*PersonalAccessTokenResponse, error) {

	if err := app.DB.Create(&u).Error; err != nil {
		return nil, err
	}

	token, err := PersonalAccessToken{}.CreateTokenForUser(u, "Login token", false)

	if err != nil {
		return nil, err
	}

	return &token, nil

}

func (_ User) FromAccessToken(token string) (*User, error) {

	var personalAccessToken PersonalAccessToken

	enc, err := app.Utilities.Crypto.EncryptWithAppKey(token)

	if err != nil {
		return nil, err
	}

	err = app.DB.Where("token = ?", enc).Preload("User").First(&personalAccessToken).Error

	if err != nil {
		return nil, err
	}

	if personalAccessToken.ExpiresAt.Before(time.Now()) {
		return nil, errors.New("token expired")
	}

	return &personalAccessToken.User, nil
}

func (_ User) LoggedInUser(c *fiber.Ctx) *User {
	return c.Locals("user").(*User)
}

func (_ User) LoggedInAccessToken(c *fiber.Ctx) string {
	return c.Locals("accessToken").(string)
}
