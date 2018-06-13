//------------------------------------------------------------------------------
// <auto-generated />
//
// This file was automatically generated by SWIG (http://www.swig.org).
// Version 3.0.12
//
// Do not make changes to this file unless you know what you are doing--modify
// the SWIG interface file instead.
//------------------------------------------------------------------------------

namespace libsbml {

 using System;
 using System.Runtime.InteropServices;

/** 
 * @sbmlpackage{core}
 *
@htmlinclude pkg-marker-core.html The priority of execution of an SBML <em>event</em>.
 *
 * The Priority object class (which was introduced in SBML Level&nbsp;3
 * Version&nbsp;1), like Delay, is derived from SBase and contains a MathML
 * formula stored in the element 'math'.  This formula is used to compute a
 * dimensionless numerical value that influences the order in which a
 * simulator is to perform the assignments of two or more events that
 * happen to be executed simultaneously.  The formula may evaluate to any
 * @c double value (and thus may be a positive or negative number, or
 * zero), with positive numbers taken to signifying a higher priority than
 * zero or negative numbers.  If no Priority object is present on a given
 * Event object, no priority is defined for that event.
 * 
 * @section priority-interp The interpretation of priorities on events in a model
 * 
 * For the purposes of SBML, <em>simultaneous event execution</em> is
 * defined as the situation in which multiple events have identical
 * times of execution.  The time of execution is calculated as the
 * sum of the time at which a given event's Trigger is <em>triggered</em>
 * plus its Delay duration, if any.  Here, <em>identical times</em> means
 * <em>mathematically equal</em> instants in time.  (In practice,
 * simulation software adhering to this specification may have to
 * rely on numerical equality instead of strict mathematical
 * equality; robust models will ensure that this difference will not
 * cause significant discrepancies from expected behavior.)
 * 
 * If no Priority subobjects are defined for two or more Event objects,
 * then those events are still executed simultaneously but their order of
 * execution is <em>undefined by the SBML Level&nbsp;3
 * specification</em>.  A software implementation may choose to execute
 * such simultaneous events in any order, as long as each event is executed
 * only once and the requirements of checking the 'persistent' attribute
 * (and acting accordingly) are satisfied.
 * 
 * If Priority subobjects are defined for two or more
 * simultaneously-triggered events, the order in which those particular
 * events must be executed is dictated by their Priority objects,
 * as follows.  If the values calculated using the two Priority
 * objects' 'math' expressions differ, then the event having
 * the higher priority value must be executed before the event with
 * the lower value.  If, instead, the two priority values are
 * mathematically equal, then the two events must be triggered in a
 * <em>random</em> order.  It is important to note that a <em>random
 *   order is not the same as an undefined order</em>: given multiple
 * runs of the same model with identical conditions, an undefined
 * ordering would permit a system to execute the events in (for
 * example) the same order every time (according to whatever scheme
 * may have been implemented by the system), whereas the explicit
 * requirement for random ordering means that the order of execution
 * in different simulation runs depends on random chance.  In other
 * words, given two events <em>A</em> and <em>B</em>, a randomly-determined
 * order must lead to an equal chance of executing <em>A</em> first or
 * <em>B</em> first, every time those two events are executed
 * simultaneously.
 * 
 * A model may contain a mixture of events, some of which have
 * Priority subobjects and some do not.  Should a combination of
 * simultaneous events arise in which some events have priorities
 * defined and others do not, the set of events with defined
 * priorities must trigger in the order determined by their Priority
 * objects, and the set of events without Priority objects must be
 * executed in an <em>undefined</em> order with respect to each other
 * and with respect to the events with Priority subobjects.  (Note
 * that <em>undefined order</em> does not necessarily mean random
 * order, although a random ordering would be a valid implementation
 * of this requirement.)
 * 
 * The following example may help further clarify these points.
 * Suppose a model contains four events that should be executed
 * simultaneously, with two of the events having Priority objects
 * with the same value and the other two events having Priority
 * objects with the same, but different, value.  The two events with
 * the higher priorities must be executed first, in a random order
 * with respect to each other, and the remaining two events must be
 * executed after them, again in a random order, for a total of four
 * possible and equally-likely event executions: A-B-C-D, A-B-D-C,
 * B-A-C-D, and B-A-D-C.  If, instead, the model contains four events
 * all having the same Priority values, there are 4! or 24
 * possible orderings, each of which must be equally likely to be
 * chosen.  Finally, if none of the four events has a Priority
 * subobject defined, or even if exactly one of the four events has a
 * defined Priority, there are again 24 possible orderings, but the
 * likelihood of choosing any particular ordering is undefined; the
 * simulator can choose between events as it wishes.  (The SBML
 * specification only defines the effects of priorities on Event
 * objects with respect to <em>other</em> Event objects with
 * priorities.  Putting a priority on a <em>single</em> Event object
 * in a model does not cause it to fall within that scope.)
 * 
 * @section priority-eval Evaluation of Priority expressions
 * 
 * An event's Priority object 'math' expression must be
 * evaluated at the time the Event is to be <em>executed</em>.  During
 * a simulation, all simultaneous events have their Priority values
 * calculated, and the event with the highest priority is selected for
 * next execution.  Note that it is possible for the execution of one
 * Event object to cause the Priority value of another
 * simultaneously-executing Event object to change (as well as to
 * trigger other events, as already noted).  Thus, after executing
 * one event, and checking whether any other events in the model have
 * been triggered, all remaining simultaneous events that
 * <em>either</em> (i) have Trigger objects with attributes
 * 'persistent'=@c false <em>or</em> (ii) have Trigger
 * expressions that did not transition from @c true to
 * @c false, must have their Priority expression reevaluated.
 * The highest-priority remaining event must then be selected for 
 * execution next.
 * 
 * @section priority-units Units of Priority object's mathematical expressions
 * 
 * The unit associated with the value of a Priority object's
 * 'math' expression should be @c dimensionless.  This is
 * because the priority expression only serves to provide a relative
 * ordering between different events, and only has meaning with
 * respect to other Priority object expressions.  The value of
 * Priority objects is not comparable to any other kind of object in
 * an SBML model.
 *
 * @note The Priority construct exists only in SBML Level&nbsp;3; it cannot
 * be used in SBML Level&nbsp;2 or Level&nbsp;1 models.
 *
 * @section priority-restrictions Restrictions relaxed in SBML Level&nbsp;3 Version&nbsp;2
 * 
 * In SBML Level&nbsp;3 Version&nbsp;2, the requirement that a Priority
 * have a 'math' subelement was relaxed, making it optional.  In
 * this case, the Priority remains undefined, and unless that information
 * is provided in some other form (such as with an SBML Level&nbsp;3
 * package), the Event behaves as if it had no Priority.
 *
 * @see Event
 * @see Delay
 * @see EventAssignment
 */

public class Priority : SBase {
	private HandleRef swigCPtr;
	
	internal Priority(IntPtr cPtr, bool cMemoryOwn) : base(libsbmlPINVOKE.Priority_SWIGUpcast(cPtr), cMemoryOwn)
	{
		//super(libsbmlPINVOKE.PriorityUpcast(cPtr), cMemoryOwn);
		swigCPtr = new HandleRef(this, cPtr);
	}
	
	internal static HandleRef getCPtr(Priority obj)
	{
		return (obj == null) ? new HandleRef(null, IntPtr.Zero) : obj.swigCPtr;
	}
	
	internal static HandleRef getCPtrAndDisown (Priority obj)
	{
		HandleRef ptr = new HandleRef(null, IntPtr.Zero);
		
		if (obj != null)
		{
			ptr             = obj.swigCPtr;
			obj.swigCMemOwn = false;
		}
		
		return ptr;
	}

  ~Priority() {
    Dispose();
  }

  public override void Dispose() {
    lock(this) {
      if (swigCPtr.Handle != global::System.IntPtr.Zero) {
        if (swigCMemOwn) {
          swigCMemOwn = false;
          libsbmlPINVOKE.delete_Priority(swigCPtr);
        }
        swigCPtr = new global::System.Runtime.InteropServices.HandleRef(null, global::System.IntPtr.Zero);
      }
      global::System.GC.SuppressFinalize(this);
      base.Dispose();
    }
  }

  
/**
   * Creates a new Priority object using the given SBML @p level and @p
   * version values.
   *
   * @param level a long integer, the SBML Level to assign to this Priority.
   *
   * @param version a long integer, the SBML Version to assign to this
   * Priority.
   *
   *
 * @throws SBMLConstructorException
 * Thrown if the given @p level and @p version combination are invalid
 * or if this object is incompatible with the given level and version.
 *
 *
   *
   *
 * @note Attempting to add an object to an SBMLDocument having a different
 * combination of SBML Level, Version and XML namespaces than the object
 * itself will result in an error at the time a caller attempts to make the
 * addition.  A parent object must have compatible Level, Version and XML
 * namespaces.  (Strictly speaking, a parent may also have more XML
 * namespaces than a child, but the reverse is not permitted.)  The
 * restriction is necessary to ensure that an SBML model has a consistent
 * overall structure.  This requires callers to manage their objects
 * carefully, but the benefit is increased flexibility in how models can be
 * created by permitting callers to create objects bottom-up if desired.  In
 * situations where objects are not yet attached to parents (e.g.,
 * SBMLDocument), knowledge of the intented SBML Level and Version help
 * libSBML determine such things as whether it is valid to assign a
 * particular value to an attribute.
 *
 *
   *
   *
 * @note The Priority construct exists only in SBML Level&nbsp;3; it
 * cannot be used in SBML Level&nbsp;2 or Level&nbsp;1 models.
 *
   *
   */ public
 Priority(long level, long version) : this(libsbmlPINVOKE.new_Priority__SWIG_0(level, version), true) {
    if (libsbmlPINVOKE.SWIGPendingException.Pending) throw libsbmlPINVOKE.SWIGPendingException.Retrieve();
  }

  
/**
   * Creates a new Priority object using the given SBMLNamespaces object
   * @p sbmlns.
   *
   *
 * 
 * The SBMLNamespaces object encapsulates SBML Level/Version/namespaces
 * information.  It is used to communicate the SBML Level, Version, and (in
 * Level&nbsp;3) packages used in addition to SBML Level&nbsp;3 Core.  A
 * common approach to using libSBML's SBMLNamespaces facilities is to create an
 * SBMLNamespaces object somewhere in a program once, then hand that object
 * as needed to object constructors that accept SBMLNamespaces as arguments.
 *
 * 
   *
   * @param sbmlns an SBMLNamespaces object.
   *
   *
 * @throws SBMLConstructorException
 * Thrown if the given @p sbmlns is inconsistent or incompatible
 * with this object.
 *
 *
   *
   *
 * @note Attempting to add an object to an SBMLDocument having a different
 * combination of SBML Level, Version and XML namespaces than the object
 * itself will result in an error at the time a caller attempts to make the
 * addition.  A parent object must have compatible Level, Version and XML
 * namespaces.  (Strictly speaking, a parent may also have more XML
 * namespaces than a child, but the reverse is not permitted.)  The
 * restriction is necessary to ensure that an SBML model has a consistent
 * overall structure.  This requires callers to manage their objects
 * carefully, but the benefit is increased flexibility in how models can be
 * created by permitting callers to create objects bottom-up if desired.  In
 * situations where objects are not yet attached to parents (e.g.,
 * SBMLDocument), knowledge of the intented SBML Level and Version help
 * libSBML determine such things as whether it is valid to assign a
 * particular value to an attribute.
 *
 *
   *
   *
 * @note The Priority construct exists only in SBML Level&nbsp;3; it
 * cannot be used in SBML Level&nbsp;2 or Level&nbsp;1 models.
 *
   */ public
 Priority(SBMLNamespaces sbmlns) : this(libsbmlPINVOKE.new_Priority__SWIG_1(SBMLNamespaces.getCPtr(sbmlns)), true) {
    if (libsbmlPINVOKE.SWIGPendingException.Pending) throw libsbmlPINVOKE.SWIGPendingException.Retrieve();
  }

  
/**
   * Copy constructor; creates a copy of this Priority.
   *
   * @param orig the object to copy.
   */ public
 Priority(Priority orig) : this(libsbmlPINVOKE.new_Priority__SWIG_2(Priority.getCPtr(orig)), true) {
    if (libsbmlPINVOKE.SWIGPendingException.Pending) throw libsbmlPINVOKE.SWIGPendingException.Retrieve();
  }

  
/**
   * Creates and returns a deep copy of this Priority object.
   *
   * @return the (deep) copy of this Priority object.
   */ public new
 Priority clone() {
    global::System.IntPtr cPtr = libsbmlPINVOKE.Priority_clone(swigCPtr);
    Priority ret = (cPtr == global::System.IntPtr.Zero) ? null : new Priority(cPtr, true);
    return ret;
  }

  
/**
   * Get the mathematical formula for the priority and return it
   * as an AST.
   * 
   * @return the math of this Priority, or @c null if the math is not set.
   */ public new
 ASTNode getMath() {
    global::System.IntPtr cPtr = libsbmlPINVOKE.Priority_getMath(swigCPtr);
    ASTNode ret = (cPtr == global::System.IntPtr.Zero) ? null : new ASTNode(cPtr, false);
    return ret;
  }

  
/**
   * Predicate to test whether the formula for this delay is set.
   *
   * @return @c true if the formula (meaning the @c math subelement) of
   * this Priority is set, @c false otherwise.
   */ public
 bool isSetMath() {
    bool ret = libsbmlPINVOKE.Priority_isSetMath(swigCPtr);
    return ret;
  }

  
/**
   * Sets the math expression of this Priority instance to a copy of the given
   * ASTNode.
   *
   * @param math an ASTNode representing a formula tree.
   *
   *
 * @return integer value indicating success/failure of the
 * function.  @if clike The value is drawn from the
 * enumeration #OperationReturnValues_t. @endif The possible values
 * returned by this function are:
 * @li @link libsbml#LIBSBML_OPERATION_SUCCESS LIBSBML_OPERATION_SUCCESS@endlink
   * @li @link libsbml#LIBSBML_INVALID_OBJECT LIBSBML_INVALID_OBJECT@endlink
   */ public new
 int setMath(ASTNode math) {
    int ret = libsbmlPINVOKE.Priority_setMath(swigCPtr, ASTNode.getCPtr(math));
    return ret;
  }

  
/**
   * Returns the libSBML type code of this object instance.
   *
   *
 * 
 * LibSBML attaches an identifying code to every kind of SBML object.  These
 * are integer constants known as <em>SBML type codes</em>.  The names of all
 * the codes begin with the characters <code>SBML_</code>.
 * @if clike The set of possible type codes for core elements is defined in
 * the enumeration #SBMLTypeCode_t, and in addition, libSBML plug-ins for
 * SBML Level&nbsp;3 packages define their own extra enumerations of type
 * codes (e.g., #SBMLLayoutTypeCode_t for the Level&nbsp;3 Layout
 * package).@endif@if java In the Java language interface for libSBML, the
 * type codes are defined as static integer constants in the interface class
 * {@link libsbmlConstants}.  @endif@if python In the Python language
 * interface for libSBML, the type codes are defined as static integer
 * constants in the interface class @link libsbml@endlink.@endif@if csharp In
 * the C# language interface for libSBML, the type codes are defined as
 * static integer constants in the interface class
 * @link libsbmlcs.libsbml@endlink.@endif  Note that different Level&nbsp;3
 * package plug-ins may use overlapping type codes; to identify the package
 * to which a given object belongs, call the 
 * <code>@if conly SBase_getPackageName()
 * @else SBase::getPackageName()
 * @endif</code>
 * method on the object.
 *
 *
   *
   * @return the SBML type code for this object:
   * @link libsbml#SBML_PRIORITY SBML_PRIORITY@endlink (default).   *
   *
 * @warning <span class='warning'>The specific integer values of the possible
 * type codes may be reused by different libSBML plug-ins for SBML Level&nbsp;3.
 * packages,  To fully identify the correct code, <strong>it is necessary to
 * invoke both getTypeCode() and getPackageName()</strong>.</span>
 *
 *
   *
   * @see getElementName()
   * @see getPackageName()
   */ public new
 int getTypeCode() {
    int ret = libsbmlPINVOKE.Priority_getTypeCode(swigCPtr);
    return ret;
  }

  
/**
   * Returns the XML element name of this object, which for Priority, is
   * always @c 'priority'.
   * 
   * @return the name of this element, i.e., @c 'priority'.
   *
   * @see getTypeCode()
   */ public new
 string getElementName() {
    string ret = libsbmlPINVOKE.Priority_getElementName(swigCPtr);
    return ret;
  }

  
/**
   * Predicate returning @c true if all the required elements for this
   * Priority object have been set.
   *
   * @note The required elements for a Priority object are:
   * @li 'math' inSBML Level&nbsp;2 and Level&nbsp;3 Version&nbsp;1.  
   *     (In SBML Level&nbsp;3 Version&nbsp;2+, it is no longer required.)
   *
   * @return a boolean value indicating whether all the required
   * elements for this object have been defined.
   */ public new
 bool hasRequiredElements() {
    bool ret = libsbmlPINVOKE.Priority_hasRequiredElements(swigCPtr);
    return ret;
  }

  
/**
   * Finds this Priority's Event parent and calls unsetPriority() on it,
   * indirectly deleting itself.
   *
   * Overridden from the SBase function since the parent is not a ListOf.
   *
   *
 * @return integer value indicating success/failure of the
 * function.  @if clike The value is drawn from the
 * enumeration #OperationReturnValues_t. @endif The possible values
 * returned by this function are:
 * @li @link libsbml#LIBSBML_OPERATION_SUCCESS LIBSBML_OPERATION_SUCCESS@endlink
   * @li @link libsbml#LIBSBML_OPERATION_FAILED LIBSBML_OPERATION_FAILED@endlink
   */ public new
 int removeFromParentAndDelete() {
    int ret = libsbmlPINVOKE.Priority_removeFromParentAndDelete(swigCPtr);
    return ret;
  }

  
/**
   *
 * Replaces all uses of a given @c SIdRef type attribute value with another
 * value.
 *
 *
 * 

 * In SBML, object identifiers are of a data type called <code>SId</code>.
 * In SBML Level&nbsp;3, an explicit data type called <code>SIdRef</code> was
 * introduced for attribute values that refer to <code>SId</code> values; in
 * previous Levels of SBML, this data type did not exist and attributes were
 * simply described to as 'referring to an identifier', but the effective
 * data type was the same as <code>SIdRef</code> in Level&nbsp;3.  These and
 * other methods of libSBML refer to the type <code>SIdRef</code> for all
 * Levels of SBML, even if the corresponding SBML specification did not
 * explicitly name the data type.
 *
 *
 *
 * This method works by looking at all attributes and (if appropriate)
 * mathematical formulas in MathML content, comparing the referenced
 * identifiers to the value of @p oldid.  If any matches are found, the
 * matching values are replaced with @p newid.  The method does @em not
 * descend into child elements.
 *
 * @param oldid the old identifier.
 * @param newid the new identifier.
 *
 *
   */ public new
 void renameSIdRefs(string oldid, string newid) {
    libsbmlPINVOKE.Priority_renameSIdRefs(swigCPtr, oldid, newid);
  }

  
/**
   *
 * Replaces all uses of a given @c UnitSIdRef type attribute value with
 * another value.
 *
 *
 * 
 * In SBML, unit definitions have identifiers of type <code>UnitSId</code>.  In
 * SBML Level&nbsp;3, an explicit data type called <code>UnitSIdRef</code> was
 * introduced for attribute values that refer to <code>UnitSId</code> values; in
 * previous Levels of SBML, this data type did not exist and attributes were
 * simply described to as 'referring to a unit identifier', but the effective
 * data type was the same as <code>UnitSIdRef</code> in Level&nbsp;3.  These and
 * other methods of libSBML refer to the type <code>UnitSIdRef</code> for all
 * Levels of SBML, even if the corresponding SBML specification did not
 * explicitly name the data type.
 *
 *
 *
 * This method works by looking at all unit identifier attribute values
 * (including, if appropriate, inside mathematical formulas), comparing the
 * referenced unit identifiers to the value of @p oldid.  If any matches
 * are found, the matching values are replaced with @p newid.  The method
 * does @em not descend into child elements.
 *
 * @param oldid the old identifier.
 * @param newid the new identifier.
 *
 *
   */ public new
 void renameUnitSIdRefs(string oldid, string newid) {
    libsbmlPINVOKE.Priority_renameUnitSIdRefs(swigCPtr, oldid, newid);
  }

  
/** */ /* libsbml-internal */ public new
 void replaceSIDWithFunction(string id, ASTNode function) {
    libsbmlPINVOKE.Priority_replaceSIDWithFunction(swigCPtr, id, ASTNode.getCPtr(function));
  }

}

}
