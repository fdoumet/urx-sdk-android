/**
 * Copyright URX 2014
 */
package com.urx.core.search.query;

/**
 * Enum type to represent all the possible schema.org Action types
 *
 * @see <a href="http://schema.org/Action">http://schema.org/Action</a>
 */
public enum ActionType implements Value {
    /**
     * Auto-generating Java enums FTW!
     * curl http://schema.rdfs.org/all-classes.csv 2> /dev/null | cut -f1 -d, | grep Action
     */
    Leave("LeaveAction"),
    Tie("TieAction"),
    Take("TakeAction"),
    Reserve("ReserveAction"),
    Want("WantAction"),
    Bookmark("BookmarkAction"),
    Marry("MarryAction"),
    Search("SearchAction"),
    Comment("CommentAction"),
    Lend("LendAction"),
    Befriend("BefriendAction"),
    CheckOut("CheckOutAction"),
    Endorse("EndorseAction"),
    Sell("SellAction"),
    Replace("ReplaceAction"),
    Draw("DrawAction"),
    Trade("TradeAction"),
    Watch("WatchAction"),
    Borrow("BorrowAction"),
    View("ViewAction"),
    Agree("AgreeAction"),
    Transfer("TransferAction"),
    Cook("CookAction"),
    Inform("InformAction"),
    Ignore("IgnoreAction"),
    Plan("PlanAction"),
    Invite("InviteAction"),
    Allocate("AllocateAction"),
    Donate("DonateAction"),
    Insert("InsertAction"),
    Order("OrderAction"),
    Win("WinAction"),
    Cancel("CancelAction"),
    Tip("TipAction"),
    Vote("VoteAction"),
    Create("CreateAction"),
    Receive("ReceiveAction"),
    Wear("WearAction"),
    Assign("AssignAction"),
    Exercise("ExerciseAction"),
    Confirm("ConfirmAction"),
    Reply("ReplyAction"),
    Disagree("DisagreeAction"),
    Join("JoinAction"),
    Update("UpdateAction"),
    Subscribe("SubscribeAction"),
    Lose("LoseAction"),
    Film("FilmAction"),
    Organize("OrganizeAction"),
    Check("CheckAction"),
    UnRegister("UnRegisterAction"),
    Prepend("PrependAction"),
    Eat("EatAction"),
    Consume("ConsumeAction"),
    Read("ReadAction"),
    Quote("QuoteAction"),
    Delete("DeleteAction"),
    Interact("InteractAction"),
    CheckIn("CheckInAction"),
    Depart("DepartAction"),
    Drink("DrinkAction"),
    Reject("RejectAction"),
    Play("PlayAction"),
    Buy("BuyAction"),
    Review("ReviewAction"),
    Share("ShareAction"),
    Paint("PaintAction"),
    Return("ReturnAction"),
    Rent("RentAction"),
    Assess("AssessAction"),
    Like("LikeAction"),
    Achieve("AchieveAction"),
    Download("DownloadAction"),
    Follow("FollowAction"),
    Dislike("DislikeAction"),
    Track("TrackAction"),
    React("ReactAction"),
    Communicate("CommunicateAction"),
    Write("WriteAction"),
    Photograph("PhotographAction"),
    Rsvp("RsvpAction"),
    Discover("DiscoverAction"),
    Append("AppendAction"),
    Ask("AskAction"),
    Schedule("ScheduleAction"),
    Apply("ApplyAction"),
    Add("AddAction"),
    Use("UseAction"),
    Move("MoveAction"),
    Travel("TravelAction"),
    Give("GiveAction"),
    Choose("ChooseAction"),
    Install("InstallAction"),
    Register("RegisterAction"),
    Pay("PayAction"),
    Listen("ListenAction"),
    Perform("PerformAction"),
    Send("SendAction"),
    Accept("AcceptAction"),
    Arrive("ArriveAction"),
    Find("FindAction"),
    Authorize("AuthorizeAction");

    protected final String repr;

    ActionType(final String repr) {
        this.repr = repr;
    }

    @Override
    public String asString() {
        return repr;
    }
}
